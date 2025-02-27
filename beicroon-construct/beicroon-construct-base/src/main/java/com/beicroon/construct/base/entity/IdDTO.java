package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class IdDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "主键")
    @NeedValue(message = "主键不能为空")
    protected Long id;

}
