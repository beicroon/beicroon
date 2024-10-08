package com.beicroon.starter.web.admin.helper;

import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.starter.web.admin.model.ResourceRoleModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public final class ResourceRoleHelper {

    private ResourceRoleHelper() {

    }

    public static boolean isRoot(ResourceRoleModel role) {
        return BooleanEnums.isTrue(role.getRootFlag());
    }

    public static boolean isNotRoot(ResourceRoleModel role) {
        return !BooleanEnums.isTrue(role.getRootFlag());
    }

    public static boolean isRoot(List<ResourceRoleModel> roles) {
        return roles.stream().anyMatch(ResourceRoleHelper::isRoot);
    }

    public static boolean isNotRoot(List<ResourceRoleModel> roles) {
        return roles.stream().noneMatch(ResourceRoleHelper::isRoot);
    }

    public static ResourceRoleModel getRoot(List<ResourceRoleModel> roles) {
        return roles.stream().filter(ResourceRoleHelper::isRoot).findFirst().orElse(null);
    }

}
