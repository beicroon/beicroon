package com.beicroon.construct.database.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DisableModel extends GenericModel {

    @ApiModelProperty(name = "禁用时间")
    @TableField(name = "`disabled_at`")
    private LocalDateTime disabledAt;

    @ApiModelProperty(name = "禁用人主键")
    @TableField(name = "`disabled_id`")
    private Long disabledId;

    @ApiModelProperty(name = "禁用人编码")
    @TableField(name = "`disabled_code`")
    private String disabledCode;

    @ApiModelProperty(name = "禁用人名称")
    @TableField(name = "`disabled_name`")
    private String disabledName;

}
