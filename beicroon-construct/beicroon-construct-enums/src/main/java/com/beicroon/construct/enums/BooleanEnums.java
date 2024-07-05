package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BooleanEnums {
    TRUE(1, "true", "是"),
    FALSE(2, "false", "否"),
    ;

    private final Integer code;

    private final String name;

    private final String desc;

    public static BooleanEnums intOf(Integer flag) {
        return BooleanEnums.isTrue(flag) ? BooleanEnums.TRUE : BooleanEnums.FALSE;
    }

    public static BooleanEnums boolOf(Boolean flag) {
        return flag != null && flag ? BooleanEnums.TRUE : BooleanEnums.FALSE;
    }

    public static boolean isTrue(Boolean flag) {
        return flag != null && flag;
    }

    public static boolean isTrue(Integer code) {
        return BooleanEnums.TRUE.getCode().equals(code);
    }

    public static boolean isTrue(String name) {
        return BooleanEnums.TRUE.getName().equals(name);
    }

    public static boolean isFalse(Boolean flag) {
        return flag == null || !flag;
    }

    public static boolean isFalse(Integer code) {
        return BooleanEnums.FALSE.getCode().equals(code);
    }

    public static boolean isFalse(String name) {
        return BooleanEnums.FALSE.getName().equals(name);
    }

}
