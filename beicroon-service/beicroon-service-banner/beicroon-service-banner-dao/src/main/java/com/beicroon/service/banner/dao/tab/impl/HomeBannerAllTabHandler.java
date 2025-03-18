package com.beicroon.service.banner.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.banner.dao.model.HomeBannerModel;
import com.beicroon.service.banner.dao.tab.HomeBannerTabHandler;
import org.springframework.stereotype.Component;

@Component
public class HomeBannerAllTabHandler implements HomeBannerTabHandler {

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
    public void fillQuery(IQueryWrapper<HomeBannerModel> query) {

    }

}
