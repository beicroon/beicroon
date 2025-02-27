package com.beicroon.service.admin.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.admin.dao.model.AdminResourceModel;
import com.beicroon.service.admin.dao.tab.AdminResourceTabHandler;
import org.springframework.stereotype.Component;

@Component
public class AdminResourceAllTabHandler implements AdminResourceTabHandler {

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
    public void fillQuery(IQueryWrapper<AdminResourceModel> query) {

    }

}
