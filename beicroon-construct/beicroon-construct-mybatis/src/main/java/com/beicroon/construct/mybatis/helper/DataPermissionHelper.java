package com.beicroon.construct.mybatis.helper;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.annotation.DataPermission;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.mybatis.expression.AdminExpression;
import com.beicroon.construct.mybatis.expression.OrgExpression;
import com.beicroon.construct.mybatis.expression.UserExpression;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.schema.Column;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class DataPermissionHelper {

    private DataPermissionHelper() {

    }

    public static Expression getDataScopeExpression(Expression where, String mappedStatementId) {
        ExpressionList<Expression> expressionList = new ExpressionList<>();

        if (where != null) {
            expressionList.add(where);
        }

        List<DataPermission> permissions = DataPermissionHelper.getPermissions(mappedStatementId);

        if (EmptyUtils.isNotEmpty(permissions)) {
            for (DataPermission permission : permissions) {
                expressionList.add(DataPermissionHelper.getExpression(permission));
            }
        }

        expressionList.add(DataPermissionHelper.getDeleteExpression());

        return expressionList;
    }

    public static List<DataPermission> getPermissions(Class<?> clazz, String method) {
        List<DataPermission> permissions = new ArrayList<>();

        DataPermission classPermission = clazz.getAnnotation(DataPermission.class);

        if (classPermission != null) {
            permissions.add(classPermission);
        }

        for (Method clazzMethod : clazz.getMethods()) {
            if (!clazzMethod.getName().equals(method)) {
                continue;
            }

            DataPermission methodPermission = clazzMethod.getAnnotation(DataPermission.class);

            if (methodPermission != null) {
                permissions.add(methodPermission);
            }
        }

        return permissions;
    }

    public static List<DataPermission> getPermissions(String mappedStatementId) {
        int index = mappedStatementId.lastIndexOf(".");

        String className = mappedStatementId.substring(0, index);

        String methodName = mappedStatementId.substring(index + 1);

        Class<?> clazz;

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        return DataPermissionHelper.getPermissions(clazz, methodName);
    }

    public static Expression getExpression(DataPermission permission) {
        ExpressionList<Expression> expression = new ExpressionList<>();

        for (DataScope scope : permission.value()) {
            switch (scope.value()) {
                case ORG:
                    expression.add(OrgExpression.getExpression(scope));
                case USER:
                    expression.add(UserExpression.getExpression(scope));
                case ADMIN:
                    expression.add(AdminExpression.getExpression(scope));
                    break;
                default:
                    throw new RuntimeException("未匹配的数据权限类型");
            }
        }

        return expression;
    }

    public static Expression getInExpression(DataScope scope, Collection<Long> ids) {
        Expression left = new Column(EmptyUtils.getValueOr(scope.filed(), scope.value().getCode()));

        ExpressionList<?> right = new ExpressionList<>(ListUtils.toList(ids, LongValue::new));

        return new InExpression(left, right);
    }

    public static Expression getEqLongExpression(DataScope scope, Long value) {
        Column left = new Column(EmptyUtils.getValueOr(scope.filed(), scope.value().getCode()));

        LongValue right = new LongValue(value);

        return new EqualsTo(left, right);
    }

    public static Expression getDeleteExpression() {
        IsNullExpression expression = new IsNullExpression();

        expression.setLeftExpression(new Column(SystemConstant.REMOVE_KEY_NAME));

        return expression;
    }

    public static Expression getAllExpression() {
        return new HexValue("1 = 1");
    }

    public static Expression getNonExpression() {
        return new HexValue("1 = 0");
    }

}
