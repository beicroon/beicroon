package com.beicroon.construct.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ClassUtils {

    private final static Map<String, Boolean> IGNORES = new HashMap<>();

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

    public static List<Field> getAllField(Object obj) {
        List<Field> allFields = new ArrayList<>();

        ClassUtils.forAllField(obj, allFields::add);

        return allFields;
    }

    public static List<Field> getAllField(Class<?> clazz) {
        List<Field> allFields = new ArrayList<>();

        ClassUtils.forAllField(clazz, allFields::add);

        return allFields;
    }

    public static void forAllField(Object obj, Consumer<Field> consumer) {
        ClassUtils.forAllField(obj.getClass(), consumer);
    }

    public static void forAllField(Class<?> clazz, Consumer<Field> consumer) {
        for (; clazz != null; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                if (ClassUtils.IGNORES.containsKey(field.getName())) {
                    continue;
                }

                consumer.accept(field);
            }
        }
    }

    public static <R> List<R> mapAllField(Class<?> clazz, Function<Field, R> function) {
        List<R> res = new ArrayList<>();

        for (; clazz != null; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                if (ClassUtils.IGNORES.containsKey(field.getName())) {
                    continue;
                }

                R r = function.apply(field);

                if (r == null) {
                    continue;
                }

                res.add(r);
            }
        }

        return res;
    }

    public static void forAllFieldAndValue(Object obj, BiConsumer<Field, Object> consumer) {
        for (Class<?> clazz = obj.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                if (ClassUtils.IGNORES.containsKey(field.getName())) {
                    continue;
                }

                field.setAccessible(true);

                Object value;

                try {
                    value = field.get(obj);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

                consumer.accept(field, value);
            }
        }
    }

    public static boolean testAllField(Object obj, Predicate<Field> predicate) {
        for (Class<?> clazz = obj.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
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
