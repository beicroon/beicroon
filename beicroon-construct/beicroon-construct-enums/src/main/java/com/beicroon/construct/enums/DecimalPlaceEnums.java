package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DecimalPlaceEnums {
    ZERO("ZeroDecimalPlace", "保留整数"),
    ONE("OneDecimalPlace", "保留1位小数"),
    TWO("TwoDecimalPlaces", "保留2位小数"),
    THREE("ThreeDecimalPlaces", "保留3位小数"),
    ;

    private final String code;

    private final String name;

}
