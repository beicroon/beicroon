package com.beicroon.service.banner.entity.homebanner.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(name = "新增轮播图入参")
@EqualsAndHashCode(callSuper = true)
public class HomeBannerCreateDTO extends GenericDTO {

    @NeedValue(message = "地址不能为空")
    @ApiModelProperty(name = "地址")
    private String url;

    @ApiModelProperty(name = "描述")
    private String description;

    @ApiModelProperty(name = "生效时间")
    private LocalDateTime validAt;

    @ApiModelProperty(name = "过期时间")
    private LocalDateTime expiredAt;

}
