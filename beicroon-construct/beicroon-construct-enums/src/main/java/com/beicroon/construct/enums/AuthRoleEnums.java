package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthRoleEnums {
    HOME("home", "用户端"),
    ADMIN("admin", "管理端"),
    STAFF("staff", "员工端"),
    RIDER("rider", "骑手端"),
    TENANT("tenant", "租户端"),
    DRIVER("driver", "司机端"),
    BUSINESS("business", "商户端"),
    ;

    private final String code;

    private final String name;

}
