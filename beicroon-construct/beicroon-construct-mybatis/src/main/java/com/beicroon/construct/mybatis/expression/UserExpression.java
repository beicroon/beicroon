package com.beicroon.construct.mybatis.expression;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.mybatis.helper.DataPermissionHelper;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;

public final class UserExpression {

    private UserExpression() {

    }

    public static Expression getExpression(Table table, DataScope scope) {
        return DataPermissionHelper.getEqLongExpression(table, scope, AuthUtils.getUser().getId());
    }

}
