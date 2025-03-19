package com.beicroon.service.banner.entity.homebanner.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(name = "编辑轮播图入参")
@EqualsAndHashCode(callSuper = true)
public class HomeBannerUpdateDTO extends IdDTO {

    @ApiModelProperty(name = "文件主键")
    private Long fileId;

    @ApiModelProperty(name = "文件名称")
    private String fileName;

    @ApiModelProperty(name = "文件地址")
    private String fileUrl;

    @ApiModelProperty(name = "描述")
    private String description;

    @ApiModelProperty(name = "生效时间")
    private LocalDateTime validAt;

    @ApiModelProperty(name = "过期时间")
    private LocalDateTime expiredAt;

}
