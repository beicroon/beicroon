package com.beicroon.construct.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ClassUtils {

    private static final Map<String, Boolean> IGNORES = new HashMap<>();

    static {
        IGNORES.put("serialVersionUID", true);
    }

    private ClassUtils() {

    }

    public static boolean isPrimitiveOrString(Object obj) {
        return ClassUtils.isPrimitiveOrString(obj.getClass());
    }

    public static boolean isPrimitiveOrString(Class<?> clazz) {
        if (clazz.isPrimitive() || clazz == String.class) {
            return true;
        }

        try {
            return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception ex) {
            return false;
        }
    }

    public static List<Field> getAllFields(Object obj) {
        List<Field> allFields = new ArrayList<>();

        ClassUtils.forAllFields(obj, allFields::add);

        return allFields;
    }

    public static void forAllFields(Object obj, Consumer<Field> consumer) {
        // 遍历继承的类
        for (Class<?> clazz = obj.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            // 获取当前类的所有属性
            for (Field field : clazz.getDeclaredFields()) {
                // 不需要的字段
                if (ClassUtils.IGNORES.containsKey(field.getName())) {
                    continue;
                }

                consumer.accept(field);
            }
        }
    }

    public static boolean testAllFields(Object obj, Predicate<Field> predicate) {
        // 遍历继承的类
        for (Class<?> clazz = obj.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            // 获取当前类的所有属性
            for (Field field : clazz.getDeclaredFields()) {
                // 不需要的字段
                if (ClassUtils.IGNORES.containsKey(field.getName())) {
                    continue;
                }

                if (!predicate.test(field)) {
                    return false;
                }
            }
        }

        return true;
    }

}
