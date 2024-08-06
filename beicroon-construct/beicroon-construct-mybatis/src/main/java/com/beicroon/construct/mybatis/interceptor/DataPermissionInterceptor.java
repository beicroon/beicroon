package com.beicroon.construct.mybatis.interceptor;

import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.database.annotation.DataPermission;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.entity.AuthUser;
import com.beicroon.construct.enums.AuthRoleEnums;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SetOperationList;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataPermissionInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        String msId = ms.getId();

        int index = msId.lastIndexOf(".");

        String className = msId.substring(0, index);

        String methodName = msId.substring(index + 1);

        Class<?> clazz;

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        List<DataPermission> permissions = this.getPermissions(clazz, methodName);

        if (EmptyUtils.isEmpty(permissions)) {
            return;
        }

        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);

        Statement statement;

        try {
            statement = CCJSqlParserUtil.parse(mpBs.sql());
        } catch (JSQLParserException ex) {
            throw ExceptionUtils.mpe("Sql解析错误: %s", ex.getCause(), mpBs.sql());
        }

        for (DataPermission permission : permissions) {
            if (statement instanceof Select) {
                this.select(permission, (Select) statement);
            }
        }

        mpBs.sql(statement.toString());
    }

    protected List<DataPermission> getPermissions(Class<?> clazz, String methodName) {
        List<DataPermission> permissions = new ArrayList<>();

        DataPermission classPermission = clazz.getAnnotation(DataPermission.class);

        if (classPermission != null) {
            permissions.add(classPermission);
        }

        for (Method method : clazz.getMethods()) {
            if (!method.getName().equals(methodName)) {
                continue;
            }

            DataPermission methodPermission = method.getAnnotation(DataPermission.class);

            if (methodPermission != null) {
                permissions.add(methodPermission);
            }
        }

        return permissions;
    }

    protected void select(DataPermission permission, Select select) {
        PlainSelect selectBody = select.getPlainSelect();

        // 单个
        if (selectBody != null) {
            this.where(permission, selectBody);
        }

        SetOperationList selectBodyList = select.getSetOperationList();

        // 多个
        if (selectBodyList != null) {
            for (Select selectItem : selectBodyList.getSelects()) {
                this.where(permission, selectItem.getPlainSelect());
            }
        }
    }

    protected void where(DataPermission permission, PlainSelect plainSelect) {
        Expression where = plainSelect.getWhere();

        Expression expression = this.getExpression(permission);

        if (where == null && expression != null) {
            plainSelect.setWhere(expression);

            return;
        }

        if (where != null && expression != null) {
            plainSelect.setWhere(new AndExpression(expression, where));

            return;
        }

        if (where != null) {
            plainSelect.setWhere(where);
        }
    }

    protected Expression getExpression(DataPermission permission) {
        String role = AuthUtils.getUser().getRole();

        if (AuthRoleEnums.HOME.getCode().equals(role)) {
            return this.getHomeExpression();
        }

        if (AuthRoleEnums.ADMIN.getCode().equals(role)) {
            return this.getAdminExpression(permission);
        }

        return this.getNonExpression();
    }

    protected Expression getHomeExpression() {
        EqualsTo permission = new EqualsTo();

        permission.setLeftExpression(new Column("`user_id`"));
        permission.setRightExpression(new LongValue(AuthUtils.getUserId()));

        return permission;
    }

    protected Expression getAdminExpression(DataPermission permission) {
        AuthUser.Permission userPermission = AuthUtils.getUser().getPermission();

        Expression expression = null;

        for (DataScope scope : permission.value()) {
            switch (scope.value()) {
                case ORG:
                    if (BooleanEnums.isTrue(userPermission.getAllOrg())) {
                        expression = this.getAllExpression();

                        break;
                    }

                    if (EmptyUtils.isEmpty(userPermission.getOrgIds())) {
                        expression = this.getNonExpression();

                        break;
                    }

                    expression = this.getInExpression(expression, scope, userPermission.getOrgIds());
                    break;
                default:
                    throw new RuntimeException("未匹配的数据权限类型");
            }
        }

        return expression;
    }

    protected Expression getInExpression(Expression expression, DataScope scope, Collection<Long> ids) {
        Expression left = new Column(EmptyUtils.getValueOr(scope.filed(), scope.value().getCode()));

        ExpressionList<?> right = new ExpressionList<>(ListUtils.toList(ids, LongValue::new));

        if (expression == null) {
            return new InExpression(left, right);
        } else {
            return new AndExpression(expression, new InExpression(left, right));
        }
    }

    protected Expression getAllExpression() {
        return new HexValue("1 = 1");
    }

    protected Expression getNonExpression() {
        return new HexValue("1 = 0");
    }

}
