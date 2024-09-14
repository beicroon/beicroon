package com.beicroon.construct.mybatis.expression;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.database.annotation.DataScope;
import com.beicroon.construct.entity.AuthUser;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.mybatis.helper.DataPermissionHelper;
import com.beicroon.construct.utils.EmptyUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;

public final class OrgExpression {

    private OrgExpression() {

    }

    public static Expression getExpression(Table table, DataScope scope) {
        AuthUser.Permission userPermission = AuthUtils.getUser().getPermission();

        if (BooleanEnums.isTrue(userPermission.getAllOrg())) {
            return DataPermissionHelper.getAllExpression();
        }

        if (EmptyUtils.isEmpty(userPermission.getOrgIds())) {
            return DataPermissionHelper.getNonExpression();
        }

        return DataPermissionHelper.getInExpression(table, scope, userPermission.getOrgIds());
    }

}
