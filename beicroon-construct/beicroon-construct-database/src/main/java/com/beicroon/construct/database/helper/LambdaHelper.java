package com.beicroon.construct.database.helper;

import com.beicroon.construct.database.lambda.SFunction;
import com.beicroon.construct.database.model.GenericModel;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class LambdaHelper {

    private static final Map<String, String> CACHE = new HashMap<>();

    private LambdaHelper() {
    }

    public static <T extends GenericModel> String getTableField(SFunction<T, ?> fieldLambda) {
        // 获取方法的唯一标识
        String key = fieldLambda.toString();

        if (LambdaHelper.CACHE.containsKey(key)) {
            return LambdaHelper.CACHE.get(key);
        }

        try {
            // 反射获取 writeReplace 方法
            Method writeReplace = fieldLambda.getClass().getDeclaredMethod("writeReplace");

            writeReplace.setAccessible(true);

            // 获取 SerializedLambda
            SerializedLambda serializedLambda = (SerializedLambda) writeReplace.invoke(fieldLambda);

            // 从 SerializedLambda 中获取方法名
            String fieldName = LambdaHelper.getFieldName(serializedLambda);

            // 缓存字段名
            LambdaHelper.CACHE.put(key, fieldName);

            return fieldName;
        } catch (Exception e) {
            throw new RuntimeException("获取字段名失败", e);
        }
    }

    public static String getFieldName(SerializedLambda serializedLambda) {
        String implMethodName = serializedLambda.getImplMethodName();

        // 去掉 get、set 前缀，并将首字母小写，推断字段名
        String fieldName;

        if (implMethodName.startsWith("get") || implMethodName.startsWith("set")) {
            fieldName = implMethodName.substring(3);
        } else if (implMethodName.startsWith("is")) {
            fieldName = implMethodName.substring(2);
        } else {
            throw new RuntimeException("无效的方法名: " + implMethodName);
        }

        // 首字母小写化
        return Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

}
