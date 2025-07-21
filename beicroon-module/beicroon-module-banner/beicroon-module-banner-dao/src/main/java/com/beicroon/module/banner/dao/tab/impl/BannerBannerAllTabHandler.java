package com.beicroon.module.banner.dao.tab.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beicroon.module.banner.dao.model.BannerBannerModel;
import com.beicroon.module.banner.dao.tab.BannerBannerTabHandler;
import org.springframework.stereotype.Component;

@Component
public class BannerBannerAllTabHandler implements BannerBannerTabHandler {

    @Override
    public boolean count() {
        return false;
    }

    @Override
    public String getCode() {
        return "all";
    }

    @Override
    public String getName() {
        return "全部";
    }

    @Override
    public void fillQuery(LambdaQueryWrapper<BannerBannerModel> query) {

    }

}
