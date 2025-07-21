package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum RecommendEnums {
    NONE("none", "无"),
    HOME("home", "首页"),
    MALL("mall", "商城页"),
    SERVICE("service", "服务页"),
    ;

    private static final Map<String, RecommendEnums> LOOKUP_MAP = new HashMap<>();

    static {
        for (RecommendEnums value : RecommendEnums.values()) {
            LOOKUP_MAP.put(value.getCode(), value);
        }
    }

    private final String code;

    private final String name;

    public static RecommendEnums codeOf(String code) {
        return LOOKUP_MAP.get(code);
    }

    public static String codeToName(String code) {
        if (!LOOKUP_MAP.containsKey(code)) {
            return null;
        }

        return LOOKUP_MAP.get(code).getName();
    }

}
