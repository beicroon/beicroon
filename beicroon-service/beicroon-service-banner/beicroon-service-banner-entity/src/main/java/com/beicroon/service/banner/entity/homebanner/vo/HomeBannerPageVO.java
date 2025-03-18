package com.beicroon.service.banner.entity.homebanner.vo;

import com.beicroon.construct.annotation.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询轮播图分页出参")
@EqualsAndHashCode(callSuper = true)
public class HomeBannerPageVO extends HomeBannerBaseVO {

}
