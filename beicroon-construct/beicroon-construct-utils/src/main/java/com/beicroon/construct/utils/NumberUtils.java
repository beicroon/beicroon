package com.beicroon.construct.utils;

import com.beicroon.construct.enums.DecimalPlaceEnums;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public final class NumberUtils {

    private NumberUtils() {

    }

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static Integer intOf(Integer val) {
        if (val == null) {
            return 0;
        }

        return val;
    }

    public static Integer intOf(String val) {
        if (EmptyUtils.isEmpty(val)) {
            return 0;
        }

        return Integer.valueOf(val);
    }

    public static Long longOf(Integer val) {
        if (val == null) {
            return 0L;
        }

        return Long.valueOf(val);
    }

    public static Long longOf(Long val) {
        if (val == null) {
            return 0L;
        }

        return val;
    }

    public static Long longOf(String val) {
        if (EmptyUtils.isEmpty(val)) {
            return 0L;
        }

        return Long.valueOf(val);
    }

    public static BigDecimal bigDecimalOf(Integer val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(val);
    }

    public static BigDecimal bigDecimalOf(Long val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(val);
    }

    public static BigDecimal bigDecimalOf(Double val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(val);
    }

    public static BigDecimal bigDecimalOf(String val) {
        if (EmptyUtils.isEmpty(val)) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal(val);
    }

    public static BigDecimal bigDecimalOf(BigDecimal val) {
        if (val == null) {
            return BigDecimal.ZERO;
        }

        return val;
    }

    public static BigDecimal bigDecimalOf(BigDecimal val, int scale) {
        return NumberUtils.bigDecimalOf(val, scale, RoundingMode.DOWN);
    }

    public static BigDecimal bigDecimalOf(BigDecimal val, int scale, RoundingMode mode) {
        if (val == null) {
            return BigDecimal.ZERO.setScale(scale, mode);
        }

        return BigDecimal.valueOf(val.doubleValue()).setScale(scale, mode);
    }

    public static Integer add(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) + NumberUtils.intOf(val2);
    }

    public static Integer subtract(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) - NumberUtils.intOf(val2);
    }

    public static Integer multiply(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) * NumberUtils.intOf(val2);
    }

    public static Integer divide(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) / NumberUtils.intOf(val2);
    }

    public static Integer remainder(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) % NumberUtils.intOf(val2);
    }

    public static boolean equals(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1).equals(NumberUtils.intOf(val2));
    }

    public static boolean gt(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) > NumberUtils.intOf(val2);
    }

    public static boolean gte(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) >= NumberUtils.intOf(val2);
    }

    public static boolean lt(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) < NumberUtils.intOf(val2);
    }

    public static boolean lte(Integer val1, Integer val2) {
        return NumberUtils.intOf(val1) <= NumberUtils.intOf(val2);
    }

    public static Long add(Long val1, Long val2) {
        return NumberUtils.longOf(val1) + NumberUtils.longOf(val2);
    }

    public static Long subtract(Long val1, Long val2) {
        return NumberUtils.longOf(val1) - NumberUtils.longOf(val2);
    }

    public static Long multiply(Long val1, Long val2) {
        return NumberUtils.longOf(val1) * NumberUtils.longOf(val2);
    }

    public static Long divide(Long val1, Long val2) {
        return NumberUtils.longOf(val1) / NumberUtils.longOf(val2);
    }

    public static Long remainder(Long val1, Long val2) {
        return NumberUtils.longOf(val1) % NumberUtils.longOf(val2);
    }

    public static boolean equals(Long val1, Long val2) {
        return NumberUtils.longOf(val1).equals(NumberUtils.longOf(val2));
    }

    public static boolean gt(Long val1, Long val2) {
        return NumberUtils.longOf(val1) > NumberUtils.longOf(val2);
    }

    public static boolean gte(Long val1, Long val2) {
        return NumberUtils.longOf(val1) >= NumberUtils.longOf(val2);
    }

    public static boolean lt(Long val1, Long val2) {
        return NumberUtils.longOf(val1) < NumberUtils.longOf(val2);
    }

    public static boolean lte(Long val1, Long val2) {
        return NumberUtils.longOf(val1) <= NumberUtils.longOf(val2);
    }

    public static BigDecimal add(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).add(NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal subtract(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).subtract(NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal multiply(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).multiply(NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.divide(val1, val2, RoundingMode.DOWN);
    }

    public static BigDecimal divide(BigDecimal val1, BigDecimal val2, RoundingMode mode) {
        return NumberUtils.bigDecimalOf(val1).divide(NumberUtils.bigDecimalOf(val2), mode);
    }

    public static BigDecimal remainder(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).remainder(NumberUtils.bigDecimalOf(val2));
    }

    public static boolean equals(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).compareTo(NumberUtils.bigDecimalOf(val2)) == 0;
    }

    public static boolean gt(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).compareTo(NumberUtils.bigDecimalOf(val2)) > 0;
    }

    public static boolean gte(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).compareTo(NumberUtils.bigDecimalOf(val2)) >= 0;
    }

    public static boolean lt(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).compareTo(NumberUtils.bigDecimalOf(val2)) < 0;
    }

    public static boolean lte(BigDecimal val1, BigDecimal val2) {
        return NumberUtils.bigDecimalOf(val1).compareTo(NumberUtils.bigDecimalOf(val2)) <= 0;
    }

    public static BigDecimal add(Integer val1, BigDecimal val2) {
        return NumberUtils.add(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal subtract(Integer val1, BigDecimal val2) {
        return NumberUtils.subtract(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal multiply(Integer val1, BigDecimal val2) {
        return NumberUtils.multiply(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(Integer val1, BigDecimal val2) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(Integer val1, BigDecimal val2, RoundingMode mode) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2), mode);
    }

    public static BigDecimal remainder(Integer val1, BigDecimal val2) {
        return NumberUtils.remainder(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean equals(Integer val1, BigDecimal val2) {
        return NumberUtils.equals(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gt(Integer val1, BigDecimal val2) {
        return NumberUtils.gt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gte(Integer val1, BigDecimal val2) {
        return NumberUtils.gte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lt(Integer val1, BigDecimal val2) {
        return NumberUtils.lt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lte(Integer val1, BigDecimal val2) {
        return NumberUtils.lte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal add(Long val1, BigDecimal val2) {
        return NumberUtils.add(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal subtract(Long val1, BigDecimal val2) {
        return NumberUtils.subtract(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal multiply(Long val1, BigDecimal val2) {
        return NumberUtils.multiply(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(Long val1, BigDecimal val2) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(Long val1, BigDecimal val2, RoundingMode mode) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2), mode);
    }

    public static BigDecimal remainder(Long val1, BigDecimal val2) {
        return NumberUtils.remainder(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean equals(Long val1, BigDecimal val2) {
        return NumberUtils.equals(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gt(Long val1, BigDecimal val2) {
        return NumberUtils.gt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gte(Long val1, BigDecimal val2) {
        return NumberUtils.gte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lt(Long val1, BigDecimal val2) {
        return NumberUtils.lt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lte(Long val1, BigDecimal val2) {
        return NumberUtils.lte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal add(BigDecimal val1, Integer val2) {
        return NumberUtils.add(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal subtract(BigDecimal val1, Integer val2) {
        return NumberUtils.subtract(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal multiply(BigDecimal val1, Integer val2) {
        return NumberUtils.multiply(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(BigDecimal val1, Integer val2) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(BigDecimal val1, Integer val2, RoundingMode mode) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2), mode);
    }

    public static BigDecimal remainder(BigDecimal val1, Integer val2) {
        return NumberUtils.remainder(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean equals(BigDecimal val1, Integer val2) {
        return NumberUtils.equals(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gt(BigDecimal val1, Integer val2) {
        return NumberUtils.gt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gte(BigDecimal val1, Integer val2) {
        return NumberUtils.gte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lt(BigDecimal val1, Integer val2) {
        return NumberUtils.lt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lte(BigDecimal val1, Integer val2) {
        return NumberUtils.lte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal add(BigDecimal val1, Long val2) {
        return NumberUtils.add(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal subtract(BigDecimal val1, Long val2) {
        return NumberUtils.subtract(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal multiply(BigDecimal val1, Long val2) {
        return NumberUtils.multiply(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(BigDecimal val1, Long val2) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal divide(BigDecimal val1, Long val2, RoundingMode mode) {
        return NumberUtils.divide(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2), mode);
    }

    public static BigDecimal remainder(BigDecimal val1, Long val2) {
        return NumberUtils.remainder(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean equals(BigDecimal val1, Long val2) {
        return NumberUtils.equals(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gt(BigDecimal val1, Long val2) {
        return NumberUtils.gt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean gte(BigDecimal val1, Long val2) {
        return NumberUtils.gte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lt(BigDecimal val1, Long val2) {
        return NumberUtils.lt(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static boolean lte(BigDecimal val1, Long val2) {
        return NumberUtils.lte(NumberUtils.bigDecimalOf(val1), NumberUtils.bigDecimalOf(val2));
    }

    public static BigDecimal getDecimalPlace(BigDecimal weight, String decimalPlace) {
        // 保留整数
        if (DecimalPlaceEnums.ZERO.getCode().equals(decimalPlace)) {
            return NumberUtils.bigDecimalOf(weight, 0);
        }
        // 保留1位小数
        else if (DecimalPlaceEnums.ONE.getCode().equals(decimalPlace)) {
            return NumberUtils.bigDecimalOf(weight, 1);
        }
        // 保留2位小数
        else if (DecimalPlaceEnums.TWO.getCode().equals(decimalPlace)) {
            return NumberUtils.bigDecimalOf(weight, 2);
        }
        // 保留3位小数
        else if (DecimalPlaceEnums.THREE.getCode().equals(decimalPlace)) {
            return NumberUtils.bigDecimalOf(weight, 3);
        }
        // 无需处理
        else {
            return NumberUtils.bigDecimalOf(weight);
        }
    }

}
