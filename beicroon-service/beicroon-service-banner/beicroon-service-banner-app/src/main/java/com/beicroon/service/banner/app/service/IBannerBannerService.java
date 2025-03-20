package com.beicroon.service.banner.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.banner.entity.bannerbanner.dto.BannerBannerCreateDTO;
import com.beicroon.service.banner.entity.bannerbanner.dto.BannerBannerQueryDTO;
import com.beicroon.service.banner.entity.bannerbanner.dto.BannerBannerUpdateDTO;
import com.beicroon.service.banner.entity.bannerbanner.vo.BannerBannerBaseVO;
import com.beicroon.service.banner.entity.bannerbanner.vo.BannerBannerDetailVO;
import com.beicroon.service.banner.entity.bannerbanner.vo.BannerBannerPageVO;

import java.util.List;

public interface IBannerBannerService {

    List<TabVO> tab(BannerBannerQueryDTO dto);

    List<BannerBannerBaseVO> list(BannerBannerQueryDTO dto);

    PageInfo<BannerBannerPageVO> page(BannerBannerQueryDTO dto);

    Boolean create(BannerBannerCreateDTO dto);

    Boolean update(BannerBannerUpdateDTO dto);

    BannerBannerBaseVO show(Long id);

    BannerBannerDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
