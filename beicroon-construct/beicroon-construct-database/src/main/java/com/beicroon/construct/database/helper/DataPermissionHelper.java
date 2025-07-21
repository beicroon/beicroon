package com.beicroon.construct.database.helper;

import com.beicroon.construct.database.annotation.DataPermission;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class DataPermissionHelper {

    private DataPermissionHelper() {

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

}
