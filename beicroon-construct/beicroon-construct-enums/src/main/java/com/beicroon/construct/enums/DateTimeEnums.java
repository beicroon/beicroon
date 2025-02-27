package com.beicroon.construct.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public enum DateTimeEnums {
    Y_M_D_H_M_S(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
    Y_M_D_H_M(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    Y_M_D_H(DateTimeFormatter.ofPattern("yyyy-MM-dd HH")),
    Y_M_D(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    Y_M(DateTimeFormatter.ofPattern("yyyy-MM")),
    H_M_S(DateTimeFormatter.ofPattern("HH:mm:ss")),
    H_M(DateTimeFormatter.ofPattern("HH:mm")),
    ;

    private final DateTimeFormatter formatter;

}
