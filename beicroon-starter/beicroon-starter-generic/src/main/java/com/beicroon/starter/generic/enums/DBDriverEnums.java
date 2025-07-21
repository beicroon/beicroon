package com.beicroon.starter.generic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DBDriverEnums {
    MYSQL("mysql", "com.mysql.cj.jdbc.Driver"),
    ;

    private final String type;

    private final String driver;

}
