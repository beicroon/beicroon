package com.beicroon.construct.database.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScopeTypeEnums {
    ORG("`org_id`", "组织"),
    USER("`creator_id`", "创建人"),
    ADMIN("`creator_id`", "创建人"),
    ;

    private final String code;

    private final String name;

}
