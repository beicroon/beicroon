package com.beicroon.construct.base.entity;

import com.beicroon.construct.base.interfaces.IAuthUser;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AuthUser implements Serializable, IAuthUser {

    @Serial
    private final static long serialVersionUID = 1L;

    private Long id = 0L;

    private String code = "";

    private String name = "";

    private String avatar = "";

    private String role = "";

    private String device = "";

}
