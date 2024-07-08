package com.beicroon.starter.mysql.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class DisableModel extends GenericModel {

    @TableField(value = "`disabled_at`")
    @ApiModelProperty(name = "禁用时间")
    private LocalDateTime disabledAt;

    @TableField(value = "`disabled_id`")
    @ApiModelProperty(name = "禁用人主键")
    private Long disabledId;

    @TableField(value = "`disabled_code`")
    @ApiModelProperty(name = "禁用人编码")
    private String disabledCode;

    @TableField(value = "`disabled_name`")
    @ApiModelProperty(name = "禁用人名称")
    private String disabledName;

}
