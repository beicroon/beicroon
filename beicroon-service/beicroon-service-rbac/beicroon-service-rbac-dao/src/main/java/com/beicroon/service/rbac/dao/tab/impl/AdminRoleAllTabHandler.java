package com.beicroon.service.rbac.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.rbac.dao.model.AdminRoleModel;
import com.beicroon.service.rbac.dao.tab.AdminRoleTabHandler;
import org.springframework.stereotype.Component;

@Component
public class AdminRoleAllTabHandler implements AdminRoleTabHandler {

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
    public void fillQuery(IQueryWrapper<AdminRoleModel> query) {

    }

}
