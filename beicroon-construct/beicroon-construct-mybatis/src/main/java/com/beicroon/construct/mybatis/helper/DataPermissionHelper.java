package com.beicroon.construct.mybatis.helper;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.annotation.DataPermission;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class DataPermissionHelper {

    private DataPermissionHelper() {

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

    public static Expression getAndExpression(Expression where, Expression scopeWhere) {
        if (where == null) {
            return scopeWhere;
        }

        return new AndExpression(where, scopeWhere);
    }

    public static Expression getInExpression(Table table, DataScope scope, Collection<Long> ids) {
        Expression left = new Column(table, EmptyUtils.getValueOr(scope.filed(), scope.value().getCode()));

        ExpressionList<?> right = new ExpressionList<>(ListUtils.toList(ids, LongValue::new));

        return new InExpression(left, right);
    }

    public static Expression getEqLongExpression(Table table, DataScope scope, Long value) {
        Column left = new Column(table, EmptyUtils.getValueOr(scope.filed(), scope.value().getCode()));

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