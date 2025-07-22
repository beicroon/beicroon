package com.beicroon.module.map.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum CarTypeEnums {
    FUEL("fuel", "普通燃油汽车", "0", null, null),
    ELECTRIC("electric", "纯电动汽车", "1", null, null),
    HYBRID("hybrid", "插电式混动汽车", "2", null, null)
    ;

    private static final Map<String, CarTypeEnums> MAP = new HashMap<>();

    static {
        for (CarTypeEnums value : CarTypeEnums.values()) {
            MAP.put(value.getCode(), value);
        }
    }

    private final String code;

    private final String name;

    private final String gaode;

    private final String baidu;

    private final String tengxun;

    public static CarTypeEnums codeOf(String code) {
        return MAP.get(code);
    }

}
