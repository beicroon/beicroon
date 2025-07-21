package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DecimalPlaceEnums {
    ZERO("zero_decimal_place", "保留整数"),
    ONE("one_decimal_place", "保留1位小数"),
    TWO("two_decimal_places", "保留2位小数"),
    THREE("three_decimal_places", "保留3位小数"),
    ;

    private final String code;

    private final String name;

}
