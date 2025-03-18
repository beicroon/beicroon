package com.beicroon.project.ronmio.dao.tab.impl;

import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceModel;
import com.beicroon.project.ronmio.dao.tab.RonmioPackingListInvoiceTabHandler;
import org.springframework.stereotype.Component;

@Component
public class RonmioPackingListInvoiceAllTabHandler implements RonmioPackingListInvoiceTabHandler {

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
    public void fillQuery(IQueryWrapper<RonmioPackingListInvoiceModel> query) {

    }

}
