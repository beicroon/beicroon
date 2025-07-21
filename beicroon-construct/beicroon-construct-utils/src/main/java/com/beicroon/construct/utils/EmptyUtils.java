package com.beicroon.construct.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public final class EmptyUtils {

    private final static long DEFAULT_LONG = 0L;

    private final static String DEFAULT_STRING = "";

    private final static float DEFAULT_FLOAT = 0.0F;

    private final static double DEFAULT_DOUBLE = 0.0;

    private final static int DEFAULT_INTEGER = 0;

    private final static BigDecimal DEFAULT_BIG_DECIMAL = BigDecimal.ZERO;

    private EmptyUtils() {

    }

    public static boolean isAllEmpty(Object object, String... ignores) {
        Map<String, String> ignoreMap = ListUtils.toMap(Arrays.asList(ignores), Function.identity());

        return ClassUtils.testAllField(object, field -> {
            if (ignoreMap.containsKey(field.getName())) {
                return true;
            }

            field.setAccessible(true);

            Object value;

            try {
                value = field.get(object);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }

            return !EmptyUtils.hasValue(value);
        });
    }

    public static String getValueOrEmpty(String value) {
        return EmptyUtils.getValueOr(value, EmptyUtils.emptyString());
    }

    public static Object getValueOrNull(Object value) {
        if (!EmptyUtils.hasValue(value)) {
            return null;
        }

        return value;
    }

    public static Object getValueOrNull(boolean condition, Object value) {
        if (!condition || !EmptyUtils.hasValue(value)) {
            return null;
        }

        return value;
    }

    public static <T> T getValueOr(T value, T or) {
        if (!EmptyUtils.hasValue(value)) {
            return or;
        }

        return value;
    }

    public static <T> T getValueOr(T value, Supplier<T> or) {
        if (!EmptyUtils.hasValue(value)) {
            return or.get();
        }

        return value;
    }

    public static boolean hasValue(Object value) {
        if (value == null) {
            return false;
        }

        if (value.getClass().isArray()) {
            return Array.getLength(value) > 0;
        }

        if (value instanceof Map) {
            return EmptyUtils.isNotEmpty((Map<?, ?>) value);
        }

        if (value instanceof Collection) {
            return EmptyUtils.isNotEmpty((Collection<?>) value);
        }

        if (value instanceof CharSequence) {
            return EmptyUtils.isNotEmpty((CharSequence) value);
        }

        if (value instanceof Long) {
            return EmptyUtils.isLong((Long) value);
        }

        if (value instanceof Float) {
            return EmptyUtils.isFloat((Float) value);
        }

        if (value instanceof Double) {
            return EmptyUtils.isDouble((Double) value);
        }

        if (value instanceof Integer) {
            return EmptyUtils.isInteger((Integer) value);
        }

        if (value instanceof BigDecimal) {
            return EmptyUtils.isBigDecimal((BigDecimal) value);
        }

        return true;
    }

    public static boolean isEmpty(Integer value) {
        return !EmptyUtils.isInteger(value);
    }

    public static <T> boolean isEmpty(T[] value) {
        return value == null || Array.getLength(value) <= 0;
    }

    public static <K, V> boolean isEmpty(Map<K, V> value) {
        return value == null || value.isEmpty();
    }

    public static <T> boolean isEmpty(Collection<T> value) {
        return value == null || value.isEmpty();
    }

    public static boolean isEmpty(CharSequence value) {
        if (value == null) {
            return true;
        }

        for (int i = 0; i < value.length(); ++i) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static <T> boolean isNotEmpty(T[] value) {
        return value != null && !EmptyUtils.isEmpty(value);
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> value) {
        return value != null && !EmptyUtils.isEmpty(value);
    }

    public static <T> boolean isNotEmpty(Collection<T> value) {
        return value != null && !EmptyUtils.isEmpty(value);
    }

    public static boolean isNotEmpty(CharSequence value) {
        return value != null && !EmptyUtils.isEmpty(value);
    }

    public static boolean isId(Long value) {
        return value != null && value > 0;
    }

    public static boolean isNotId(Long value) {
        return !EmptyUtils.isId(value);
    }

    public static boolean isLong(Long value) {
        return value != null && !value.equals(EmptyUtils.DEFAULT_LONG);
    }

    public static boolean isFloat(Float value) {
        return value != null && !value.equals(EmptyUtils.DEFAULT_FLOAT);
    }

    public static boolean isDouble(Double value) {
        return value != null && !value.equals(EmptyUtils.DEFAULT_DOUBLE);
    }

    public static boolean isInteger(Integer value) {
        return value != null && !value.equals(EmptyUtils.DEFAULT_INTEGER);
    }

    public static boolean isBigDecimal(BigDecimal value) {
        return value != null && EmptyUtils.DEFAULT_BIG_DECIMAL.compareTo(value) != 0;
    }

    public static boolean isLng(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.valueOf(-180L)) >= 0 && value.compareTo(BigDecimal.valueOf(180L)) <= 0;
    }

    public static boolean isLat(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.valueOf(-90L)) >= 0 && value.compareTo(BigDecimal.valueOf(90L)) <= 0;
    }

    public static <T> Set<T> emptySet() {
        return new HashSet<>();
    }

    public static <T> List<T> emptyList() {
        return new ArrayList<>();
    }

    public static <K, V> Map<K, V> emptyMap() {
        return new HashMap<>();
    }

    public static long emptyLong() {
        return EmptyUtils.DEFAULT_LONG;
    }

    public static String emptyString() {
        return EmptyUtils.DEFAULT_STRING;
    }

    public static float emptyFloat() {
        return EmptyUtils.DEFAULT_FLOAT;
    }

    public static double emptyDouble() {
        return EmptyUtils.DEFAULT_DOUBLE;
    }

    public static int emptyInteger() {
        return EmptyUtils.DEFAULT_INTEGER;
    }

    public static BigDecimal emptyBigDecimal() {
        return EmptyUtils.DEFAULT_BIG_DECIMAL;
    }

}
