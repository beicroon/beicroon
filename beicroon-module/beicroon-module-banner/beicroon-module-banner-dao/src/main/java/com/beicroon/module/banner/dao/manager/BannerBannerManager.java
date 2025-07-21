package com.beicroon.module.banner.dao.manager;

import com.beicroon.module.banner.dao.convertor.BannerBannerConvertor;
import com.beicroon.module.banner.dao.repository.BannerBannerRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BannerBannerManager {

    @Resource
    private BannerBannerConvertor bannerBannerConvertor;

    @Resource
    private BannerBannerRepository bannerBannerRepository;

}
