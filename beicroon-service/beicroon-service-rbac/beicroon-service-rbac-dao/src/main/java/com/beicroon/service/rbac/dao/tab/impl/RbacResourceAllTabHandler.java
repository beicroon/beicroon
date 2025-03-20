package com.beicroon.service.rbac.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.rbac.dao.model.RbacResourceModel;
import com.beicroon.service.rbac.dao.tab.RbacResourceTabHandler;
import org.springframework.stereotype.Component;

@Component
public class RbacResourceAllTabHandler implements RbacResourceTabHandler {

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
    public void fillQuery(IQueryWrapper<RbacResourceModel> query) {

    }

}
