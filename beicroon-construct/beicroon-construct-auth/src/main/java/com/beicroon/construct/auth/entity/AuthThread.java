package com.beicroon.construct.auth.entity;

import com.beicroon.construct.base.entity.AuthUser;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AuthThread implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    private AuthUser authUser;

    private String source;

    private String version;

    private Long tenantId;

    private Long stationId;

}
