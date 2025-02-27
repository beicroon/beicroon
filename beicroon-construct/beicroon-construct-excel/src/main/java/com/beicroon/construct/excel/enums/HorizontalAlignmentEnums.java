package com.beicroon.construct.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HorizontalAlignmentEnums {
    CENTER("center", "居中"),
    ;

    private final String code;

    private final String name;

}
