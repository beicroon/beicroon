package com.beicroon.starter.mysql.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class GenericModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`")
    @ApiModelProperty(name = "主键")
    private Long id;

    @TableField(value = "`created_at`")
    @ApiModelProperty(name = "创建时间")
    private LocalDateTime createdAt;

    @TableField(value = "`creator_id`")
    @ApiModelProperty(name = "创建人主键")
    private Long creatorId;

    @TableField(value = "`creator_code`")
    @ApiModelProperty(name = "创建人编码")
    private String creatorCode;

    @TableField(value = "`creator_name`")
    @ApiModelProperty(name = "创建人名称")
    private String creatorName;

    @TableField(value = "`modified_at`")
    @ApiModelProperty(name = "更新时间")
    private LocalDateTime modifiedAt;

    @TableField(value = "`modifier_id`")
    @ApiModelProperty(name = "更新人主键")
    private Long modifierId;

    @TableField(value = "`modifier_code`")
    @ApiModelProperty(name = "更新人编码")
    private String modifierCode;

    @TableField(value = "`modifier_name`")
    @ApiModelProperty(name = "更新人名称")
    private String modifierName;

}
