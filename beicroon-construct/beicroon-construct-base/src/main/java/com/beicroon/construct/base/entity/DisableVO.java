package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class DisableVO extends GenericVO {

    @ApiModelProperty(name = "禁用时间")
    private LocalDateTime disabledAt;

    @ApiModelProperty(name = "禁用人主键")
    private Long disabledId;

    @ApiModelProperty(name = "禁用人编码")
    private String disabledCode;

    @ApiModelProperty(name = "禁用人名称")
    private String disabledName;

}
