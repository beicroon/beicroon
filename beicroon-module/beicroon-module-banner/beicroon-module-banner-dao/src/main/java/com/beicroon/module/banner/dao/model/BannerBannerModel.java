package com.beicroon.module.banner.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "`banner_banner`")
public class BannerBannerModel extends GenericModel {

    @TableField(value = "`banner_url`")
    @Schema(name = "bannerUrl", description = "轮播图链接")
    private String bannerUrl;

    @TableField(value = "`banner_remark`")
    @Schema(name = "bannerRemark", description = "轮播图备注")
    private String bannerRemark;

    @TableField(value = "`banner_sorting`")
    @Schema(name = "bannerSorting", description = "轮播图排序")
    private Integer bannerSorting;

    @TableField(value = "`banner_effect_at`")
    @Schema(name = "bannerEffectAt", description = "轮播图生效时间")
    private LocalDateTime bannerEffectAt;

    @TableField(value = "`banner_expired_at`")
    @Schema(name = "bannerExpiredAt", description = "轮播图过期时间")
    private LocalDateTime bannerExpiredAt;

    @TableField(value = "`redirect_url`")
    @Schema(name = "redirectUrl", description = "轮播图跳转地址")
    private String redirectUrl;

}
