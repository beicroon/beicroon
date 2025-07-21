package com.beicroon.construct.http.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpMethodEnums {
    GET("GET"),
    POST("POST"),
    ;

    private final String name;

}
