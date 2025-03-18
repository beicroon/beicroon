package com.beicroon.service.banner.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerCreateDTO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerQueryDTO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerUpdateDTO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerBaseVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerDetailVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerPageVO;

import java.util.List;

public interface IHomeBannerService {

    List<TabVO> tab(HomeBannerQueryDTO dto);

    List<HomeBannerBaseVO> list(HomeBannerQueryDTO dto);

    PageInfo<HomeBannerPageVO> page(HomeBannerQueryDTO dto);

    Boolean create(HomeBannerCreateDTO dto);

    Boolean update(HomeBannerUpdateDTO dto);

    HomeBannerBaseVO show(Long id);

    HomeBannerDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
