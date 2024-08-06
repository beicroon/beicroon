package com.beicroon.construct.database.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScopeTypeEnums {
    ORG("`org_id`", "租住"),
    ;

    private final String code;

    private final String name;

}
