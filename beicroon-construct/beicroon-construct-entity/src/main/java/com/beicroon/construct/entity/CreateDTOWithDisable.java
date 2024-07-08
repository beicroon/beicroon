package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateDTOWithDisable extends CreateDTO {

    @ApiModelProperty(name = "是否禁用")
    private Integer disableFlag;

}
