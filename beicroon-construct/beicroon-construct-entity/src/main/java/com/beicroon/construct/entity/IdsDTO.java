package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.validator.annotation.NeedValue;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class IdsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NeedValue(message = "主键列表不能为空")
    @ApiModelProperty(name = "主键列表")
    protected List<Long> ids;

}
