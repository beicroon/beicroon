package com.beicroon.construct.utils;

import com.beicroon.construct.constant.RegexConstant;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class StringUtils {

    private StringUtils() {

    }

    public static String toCaseSnake(String value) {
        if (EmptyUtils.isEmpty(value)) {
            return EmptyUtils.emptyString();
        }

        return value.replaceAll(RegexConstant.CASE_SNAKE, "_$1").toLowerCase();
    }

    public static String splitAndToFirstUpper(String value, String separator) {
        return StringUtils.toFirstUpper(StringUtils.split(value, separator));
    }

    public static String toFirstUpper(Collection<String> values) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptyString();
        }

        return values.stream()
                .map(StringUtils::toFirstUpper)
                .collect(Collectors.joining());
    }

    public static String toFirstUpper(String... values) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptyString();
        }

        return Arrays.stream(values)
                .map(StringUtils::toFirstUpper)
                .collect(Collectors.joining());
    }

    public static String toFirstLower(String value) {
        if (EmptyUtils.isEmpty(value)) {
            return EmptyUtils.emptyString();
        }

        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String toFirstUpper(String value) {
        if (EmptyUtils.isEmpty(value)) {
            return EmptyUtils.emptyString();
        }

        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static <T, R> String join(Collection<T> collection, Function<? super T, ? extends R> key, String separator) {
        return StringUtils.join(ListUtils.toSet(collection, key), separator);
    }

    public static String join(Collection<?> collection, String separator) {
        if (EmptyUtils.isEmpty(collection)) {
            return EmptyUtils.emptyString();
        }

        Iterator<?> iterator = collection.iterator();

        StringBuilder sb = new StringBuilder();

        while (iterator.hasNext()) {
            Object value = iterator.next();

            if (value != null) {
                sb.append(value);
            }

            if (iterator.hasNext()) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static String join(String[] values, String separator) {
        return String.join(separator, values);
    }

    public static List<String> split(String value, String regex) {
        return StringUtils.split(value, regex, 0);
    }

    public static List<Long> splitToLong(String value, String regex) {
        return StringUtils.splitToLong(value, regex, 0);
    }

    public static List<String> split(String value, String regex, int limit) {
        if (EmptyUtils.isEmpty(value)) {
            return EmptyUtils.emptyList();
        }

        return Arrays.asList(value.split(regex, limit));
    }

    public static List<Long> splitToLong(String value, String regex, int limit) {
        if (EmptyUtils.isEmpty(value)) {
            return EmptyUtils.emptyList();
        }

        return Arrays.stream(value.split(regex, limit)).map(Long::valueOf).collect(Collectors.toList());
    }

}
