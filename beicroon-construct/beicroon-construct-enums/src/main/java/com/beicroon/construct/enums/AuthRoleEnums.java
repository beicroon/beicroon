package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthRoleEnums {
    HOME("HOME", "用户端"),
    ADMIN("ADMIN", "管理端"),
    ;

    private final String code;

    private final String name;

}
