package com.beicroon.construct.base.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GenericVO extends BaseVO {

    @Schema(name = "id", description = "主键")
    private Long id;

    @Schema(name = "sourceName", description = "数据来源")
    private String sourceName;

    @Schema(name = "sourceVersion", description = "来源版本号")
    private String sourceVersion;

    @Schema(name = "creatorId", description = "创建者主键")
    private Long creatorId;

    @Schema(name = "createdAt", description = "创建时间")
    private LocalDateTime createdAt;

    @Schema(name = "creatorCode", description = "创建者编码")
    private String creatorCode;

    @Schema(name = "creatorName", description = "创建者名称")
    private String creatorName;

    @Schema(name = "modifiedAt", description = "更新时间")
    private LocalDateTime modifiedAt;

    @Schema(name = "modifierId", description = "更新人主键")
    private Long modifierId;

    @Schema(name = "modifierCode", description = "更新人编码")
    private String modifierCode;

    @Schema(name = "modifierName", description = "更新人名称")
    private String modifierName;

}
