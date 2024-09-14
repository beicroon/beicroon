package com.beicroon.construct.mybatis.expression;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.mybatis.helper.DataPermissionHelper;
import net.sf.jsqlparser.expression.Expression;

public final class UserExpression {

    private UserExpression() {

    }

    public static Expression getExpression(DataScope scope) {
        return DataPermissionHelper.getEqLongExpression(scope, AuthUtils.getUser().getId());
    }

}
