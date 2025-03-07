package com.beicroon.service.admin.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.admin.dao.model.AdminAccountModel;
import com.beicroon.service.admin.dao.tab.AdminAccountTabHandler;
import org.springframework.stereotype.Component;

@Component
public class AdminAccountAllTabHandler implements AdminAccountTabHandler {

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
    public void fillQuery(IQueryWrapper<AdminAccountModel> query) {

    }

}
