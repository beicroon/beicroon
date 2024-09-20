package com.beicroon.starter.web.admin.helper;

import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.starter.web.admin.model.ResourceRoleModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ResourceRoleHelper {

    private ResourceRoleHelper() {

    }

    public static boolean isRoot(ResourceRoleModel role) {
        return BooleanEnums.isTrue(role.getRootFlag());
    }

}
