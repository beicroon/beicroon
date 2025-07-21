package com.beicroon.construct.excel.helper;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public final class ExcelHelper {

    private ExcelHelper() {

    }

    public static Object getValue(String value, Field field) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        Class<?> type = field.getType();

        if (String.class.equals(type)) {
            return value;
        }

        if (Integer.class.equals(type) || int.class.equals(type)) {
            return Integer.valueOf(value);
        }

        if (Long.class.equals(type) || long.class.equals(type)) {
            return Long.valueOf(value);
        }

        if (Double.class.equals(type) || double.class.equals(type)) {
            return Double.valueOf(value);
        }

        if (BigDecimal.class.equals(type)) {
            return new BigDecimal(value);
        }

        if (Boolean.class.equals(type) || boolean.class.equals(type)) {
            return Boolean.valueOf(value);
        }

        return null;
    }

}
