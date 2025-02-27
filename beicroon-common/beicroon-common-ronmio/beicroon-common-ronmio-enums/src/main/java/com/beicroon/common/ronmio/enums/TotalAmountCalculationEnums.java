package com.beicroon.common.ronmio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TotalAmountCalculationEnums {
    UNIT_PRICE_COUNT("UnitPrice*Count", "单价*数量"),
    UNIT_PRICE_VOLUME("UnitPrice*Volume", "单价*体积")
    ;

    private final String code;

    private final String name;

}
