package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BooleanEnums {
    TRUE("true", "是"),
    FALSE("false", "否"),
    ;

    private final String code;

    private final String name;

    public boolean isTrue() {
        return this.code.equals(BooleanEnums.TRUE.getCode());
    }

    public static BooleanEnums boolOf(Boolean flag) {
        return flag != null && flag ? BooleanEnums.TRUE : BooleanEnums.FALSE;
    }

    public static BooleanEnums codeOf(String code) {
        return BooleanEnums.TRUE.getCode().equals(code) ? BooleanEnums.TRUE : BooleanEnums.FALSE;
    }

    public static BooleanEnums nameOf(String name) {
        return BooleanEnums.TRUE.getName().equals(name) ? BooleanEnums.TRUE : BooleanEnums.FALSE;
    }

    public static boolean isTrue(Boolean flag) {
        return flag != null && flag;
    }

    public static boolean isFalse(Boolean flag) {
        return !BooleanEnums.isTrue(flag);
    }

    public static boolean isTrue(String code) {
        return BooleanEnums.TRUE.getCode().equals(code);
    }

    public static boolean isFalse(String code) {
        return !BooleanEnums.isTrue(code);
    }

}
