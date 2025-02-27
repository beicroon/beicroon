package com.beicroon.construct.database.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScopeTypeEnums {
    CREATOR("`creator_id`", "创建人", "creatorScope"),
    ;

    private final String code;

    private final String name;

    private final String bean;

}
