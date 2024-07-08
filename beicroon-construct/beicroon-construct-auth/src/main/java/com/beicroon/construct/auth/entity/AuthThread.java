package com.beicroon.construct.auth.entity;

import com.beicroon.construct.entity.AuthUser;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AuthThread implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private AuthUser authUser;

    private String source;

    private String version;

    private Long tenantId;

    private Long stationId;

}
