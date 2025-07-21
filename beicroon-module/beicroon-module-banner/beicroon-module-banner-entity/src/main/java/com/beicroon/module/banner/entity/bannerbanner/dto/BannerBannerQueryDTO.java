package com.beicroon.module.banner.entity.bannerbanner.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BannerBannerQueryDTO extends QueryDTO {

    @FieldSearch(value = "`banner_remark`", like = true)
    @Schema(name = "bannerRemark", description = "轮播图备注")
    private String bannerRemark;

    @FieldSearch(value = "`banner_effect_at`", ge = true)
    @Schema(name = "bannerEffectAt", description = "轮播图生效时间")
    private LocalDateTime bannerEffectAt;

    @FieldSearch(value = "`banner_expired_at`", lt = true)
    @Schema(name = "bannerExpiredAt", description = "轮播图过期时间")
    private LocalDateTime bannerExpiredAt;

}
