package com.beicroon.construct.base.entity;

import com.beicroon.construct.base.annotation.NeedValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class IdsDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @NeedValue(message = "主键列表不能为空")
    @Schema(name = "ids", description = "主键列表")
    protected List<Long> ids;

}
