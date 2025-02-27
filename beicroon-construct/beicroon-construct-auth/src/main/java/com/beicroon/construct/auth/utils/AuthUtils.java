package com.beicroon.construct.auth.utils;

import com.beicroon.construct.auth.entity.AuthThread;
import com.beicroon.construct.base.entity.AuthUser;
import com.beicroon.construct.utils.EmptyUtils;

public final class AuthUtils {

    private final static ThreadLocal<AuthThread> AUTH_THREAD_LOCAL = new InheritableThreadLocal<>();

    private AuthUtils() {

    }

    public static AuthUser newSystem() {
        AuthUser system = new AuthUser();

        system.setId(0L);
        system.setCode("system");
        system.setName("系统");

        return system;
    }

    public static AuthThread getAuthThreadInfo() {
        AuthThread info = AuthUtils.AUTH_THREAD_LOCAL.get();

        if (info != null) {
            return info;
        }

        AuthThread newInfo = new AuthThread();

        newInfo.setAuthUser(AuthUtils.newSystem());
        newInfo.setSource(EmptyUtils.emptyString());
        newInfo.setVersion(EmptyUtils.emptyString());
        newInfo.setTenantId(EmptyUtils.emptyLong());

        AuthUtils.AUTH_THREAD_LOCAL.set(newInfo);

        return newInfo;
    }

    public static void setAuthThreadInfo(AuthThread info) {
        AuthUtils.AUTH_THREAD_LOCAL.set(info);
    }

    public static void setUser(AuthUser user) {
        AuthUtils.getAuthThreadInfo().setAuthUser(user);
    }

    public static AuthUser getUser() {
        return AuthUtils.getAuthThreadInfo().getAuthUser();
    }

    public static Long getUserId() {
        return AuthUtils.getUser().getId();
    }

    public static void setSource(String source) {
        AuthUtils.getAuthThreadInfo().setSource(source);
    }

    public static String getSource() {
        return AuthUtils.getAuthThreadInfo().getSource();
    }

    public static void setVersion(String version) {
        AuthUtils.getAuthThreadInfo().setVersion(version);
    }

    public static String getVersion() {
        return AuthUtils.getAuthThreadInfo().getVersion();
    }

    public static void setTenantId(Long tenantId) {
        AuthUtils.getAuthThreadInfo().setTenantId(tenantId);
    }

    public static Long getTenantId() {
        return AuthUtils.getAuthThreadInfo().getTenantId();
    }

    public static void clear() {
        AuthUtils.AUTH_THREAD_LOCAL.remove();
    }

}
