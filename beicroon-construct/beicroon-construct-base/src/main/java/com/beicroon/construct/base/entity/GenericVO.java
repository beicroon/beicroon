package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class GenericVO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "主键")
    private Long id;

    @ApiModelProperty(name = "创建者id")
    private Long creatorId;

    @ApiModelProperty(name = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(name = "创建者编码")
    private String creatorCode;

    @ApiModelProperty(name = "创建者昵称")
    private String creatorName;

    @ApiModelProperty(name = "更新时间")
    private LocalDateTime modifiedAt;

    @ApiModelProperty(name = "更新人id")
    private Long modifierId;

    @ApiModelProperty(name = "更新人编码")
    private String modifierCode;

    @ApiModelProperty(name = "更新人昵称")
    private String modifierName;

}
