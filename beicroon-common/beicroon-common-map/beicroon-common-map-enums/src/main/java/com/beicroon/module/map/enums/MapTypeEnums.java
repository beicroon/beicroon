package com.beicroon.module.map.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum MapTypeEnums {
    GAODE("GAODE", "高德"),
    BAIDU("BAIDU", "百度"),
    TENGXUN("TENGXUN", "腾讯"),
    ;

    private static final Map<String, MapTypeEnums> MAP = new HashMap<>();

    static {
        for (MapTypeEnums value : MapTypeEnums.values()) {
            MAP.put(value.getCode(), value);
        }
    }

    private final String code;

    private final String name;

    public static MapTypeEnums codeOf(String code) {
        return MAP.get(code);
    }

}
