package com.beicroon.module.banner.entity.bannerbanner.dto;

import com.beicroon.construct.base.annotation.NeedValue;
import io.swagger.v3.oas.annotations.media.Schema;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BannerBannerUpdateDTO extends IdDTO {

    @NeedValue(message = "轮播图链接不能为空")
    @Schema(name = "bannerUrl", description = "轮播图链接")
    private String bannerUrl;

    @Schema(name = "bannerRemark", description = "轮播图备注")
    private String bannerRemark;

    @Schema(name = "bannerSorting", description = "轮播图排序")
    private Integer bannerSorting;

    @Schema(name = "bannerEffectAt", description = "轮播图生效时间")
    private LocalDateTime bannerEffectAt;

    @Schema(name = "bannerExpiredAt", description = "轮播图过期时间")
    private LocalDateTime bannerExpiredAt;

    @Schema(name = "redirectUrl", description = "轮播图跳转地址")
    private String redirectUrl;

}
