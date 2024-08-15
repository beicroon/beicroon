package com.beicroon.starter.generic.entity;

import java.io.Serial;
import java.io.Serializable;

public record Field(String name, String type, String comment) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
