package com.beicroon.service.banner.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.banner.dao.model.BannerBannerModel;
import com.beicroon.service.banner.dao.tab.BannerBannerTabHandler;
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
    public void fillQuery(IQueryWrapper<BannerBannerModel> query) {

    }

}
