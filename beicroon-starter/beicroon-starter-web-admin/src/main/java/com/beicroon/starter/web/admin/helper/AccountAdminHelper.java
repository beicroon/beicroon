package com.beicroon.starter.web.admin.helper;

import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.starter.web.admin.model.AccountAdminModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public final class AccountAdminHelper {

    private AccountAdminHelper() {

    }

    public static boolean isRoot(AccountAdminModel admin) {
        return BooleanEnums.isTrue(admin.getRootFlag());
    }

    public static boolean isNotRoot(AccountAdminModel admin) {
        return !BooleanEnums.isTrue(admin.getRootFlag());
    }

    public static boolean isRoot(List<AccountAdminModel> admins) {
        return admins.stream().anyMatch(AccountAdminHelper::isRoot);
    }

    public static boolean isNotRoot(List<AccountAdminModel> admins) {
        return admins.stream().noneMatch(AccountAdminHelper::isRoot);
    }

    public static AccountAdminModel getRoot(List<AccountAdminModel> admins) {
        return admins.stream().filter(AccountAdminHelper::isRoot).findFirst().orElse(null);
    }

}
