package com.beicroon.construct.exception.utils;

import com.beicroon.construct.exception.BusinessException;

import java.lang.reflect.Array;

public final class ExceptionUtils {

    private ExceptionUtils() {

    }

    public static BusinessException business(String message, Object... args) {
        if (Array.getLength(args) > 0) {
            return new BusinessException(String.format(message, args));
        }

        return new BusinessException(message);
    }

    public static BusinessException business(Integer code, String message) {
        return new BusinessException(code, message);
    }

    public static BusinessException authorized() {
        return new BusinessException(401, "登录异常");
    }

    public static BusinessException signature() {
        return new BusinessException(401, "签名异常");
    }

    public static BusinessException forbidden() {
        return new BusinessException(403, "权限异常");
    }

    public static BusinessException forbidden(String message) {
        return new BusinessException(403, message);
    }

}
