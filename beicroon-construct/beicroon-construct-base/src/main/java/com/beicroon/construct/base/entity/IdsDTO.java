package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class IdsDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "主键列表")
    @NeedValue(message = "主键列表不能为空")
    protected List<Long> ids;

}
