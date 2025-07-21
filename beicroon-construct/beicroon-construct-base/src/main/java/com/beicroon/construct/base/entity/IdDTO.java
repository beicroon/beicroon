package com.beicroon.construct.base.entity;

import com.beicroon.construct.base.annotation.NeedValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class IdDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @NeedValue(message = "主键不能为空")
    @Schema(name = "id", description = "主键")
    protected Long id;

}
