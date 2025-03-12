package com.beicroon.service.ronmio.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.service.ronmio.dao.model.RonmioCustomerModel;
import com.beicroon.service.ronmio.dao.tab.RonmioCustomerTabHandler;
import org.springframework.stereotype.Component;

@Component
public class RonmioCustomerAllTabHandler implements RonmioCustomerTabHandler {

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
    public void fillQuery(IQueryWrapper<RonmioCustomerModel> query) {

    }

}
