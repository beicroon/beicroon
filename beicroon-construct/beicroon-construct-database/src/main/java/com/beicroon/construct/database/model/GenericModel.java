package com.beicroon.construct.database.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class GenericModel implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "主键")
    @TableId(value = "`id`")
    private Long id;

    @ApiModelProperty(name = "创建时间")
    @TableField(name = "`created_at`")
    private LocalDateTime createdAt;

    @ApiModelProperty(name = "创建人主键")
    @TableField(name = "`creator_id`")
    private Long creatorId;

    @ApiModelProperty(name = "创建人编码")
    @TableField(name = "`creator_code`")
    private String creatorCode;

    @ApiModelProperty(name = "创建人名称")
    @TableField(name = "`creator_name`")
    private String creatorName;

    @ApiModelProperty(name = "更新时间")
    @TableField(name = "`modified_at`")
    private LocalDateTime modifiedAt;

    @ApiModelProperty(name = "更新人主键")
    @TableField(name = "`modifier_id`")
    private Long modifierId;

    @ApiModelProperty(name = "更新人编码")
    @TableField(name = "`modifier_code`")
    private String modifierCode;

    @ApiModelProperty(name = "更新人名称")
    @TableField(name = "`modifier_name`")
    private String modifierName;

}
