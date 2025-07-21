package com.beicroon.construct.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum PaymentTypeEnums {
    NONE("none", "无需支付"),
    WECHAT("wechat", "微信"),
//    ALIPAY("alipay", "支付宝"),
    BALANCE("balance", "余额"),
    SCORE("score", "积分"),
    ;

    private static final Map<String, PaymentTypeEnums> LOOKUP_MAP = new HashMap<>();

    static {
        for (PaymentTypeEnums value : PaymentTypeEnums.values()) {
            LOOKUP_MAP.put(value.getCode(), value);
        }
    }

    private final String code;

    private final String name;

    public static PaymentTypeEnums codeOf(String code) {
        return LOOKUP_MAP.get(code);
    }

    public static String codeToName(String code) {
        if (!LOOKUP_MAP.containsKey(code)) {
            return null;
        }

        return LOOKUP_MAP.get(code).getName();
    }

}
