package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UpdateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "主键")
    protected Long id;

}
