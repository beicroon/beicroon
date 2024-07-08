package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class GenericVO extends BaseVO {

    @ApiModelProperty(name = "主键")
    private Long id;

    @ApiModelProperty(name = "创建者id")
    private Long creatorId;

    @ApiModelProperty(name = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(name = "创建者编码")
    private String creatorCode;

    @ApiModelProperty(name = "创建者姓名")
    private String creatorName;

    @ApiModelProperty(name = "更新时间")
    private LocalDateTime modifiedAt;

    @ApiModelProperty(name = "更新人id")
    private Long modifierId;

    @ApiModelProperty(name = "更新人编码")
    private String modifierCode;

    @ApiModelProperty(name = "更新人姓名")
    private String modifierName;

}
