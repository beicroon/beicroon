package com.beicroon.construct.database.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @TableId(value = "`id`")
    @Schema(name = "id", description = "主键")
    private Long id;

    @TableField(value = "`source_name`")
    @Schema(name = "sourceName", description = "数据来源")
    private String sourceName;

    @TableField(value = "`source_version`")
    @Schema(name = "sourceVersion", description = "来源版本号")
    private String sourceVersion;

    @TableField(value = "`created_at`")
    @Schema(name = "createdAt", description = "创建时间")
    private LocalDateTime createdAt;

    @TableField(value = "`creator_id`")
    @Schema(name = "creatorId", description = "创建人主键")
    private Long creatorId;

    @TableField(value = "`creator_code`")
    @Schema(name = "creatorCode", description = "创建人编码")
    private String creatorCode;

    @TableField(value = "`creator_name`")
    @Schema(name = "creatorName", description = "创建人名称")
    private String creatorName;

    @TableField(value = "`modified_at`")
    @Schema(name = "modifiedAt", description = "更新时间")
    private LocalDateTime modifiedAt;

    @TableField(value = "`modifier_id`")
    @Schema(name = "modifierId", description = "更新人主键")
    private Long modifierId;

    @TableField(value = "`modifier_code`")
    @Schema(name = "modifierCode", description = "更新人编码")
    private String modifierCode;

    @TableField(value = "`modifier_name`")
    @Schema(name = "modifierName", description = "更新人名称")
    private String modifierName;

}
