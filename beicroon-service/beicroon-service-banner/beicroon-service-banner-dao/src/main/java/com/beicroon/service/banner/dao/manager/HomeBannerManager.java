package com.beicroon.service.banner.dao.manager;

import com.beicroon.service.banner.dao.convertor.HomeBannerConvertor;
import com.beicroon.service.banner.dao.repository.HomeBannerRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HomeBannerManager {

    @Resource
    private HomeBannerConvertor homeBannerConvertor;

    @Resource
    private HomeBannerRepository homeBannerRepository;

}
