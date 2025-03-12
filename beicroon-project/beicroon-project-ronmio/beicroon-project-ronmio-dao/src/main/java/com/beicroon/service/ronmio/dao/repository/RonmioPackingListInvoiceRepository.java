package com.beicroon.service.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.ronmio.dao.mapper.RonmioPackingListInvoiceMapper;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoiceModel;
import com.beicroon.service.ronmio.dao.tab.RonmioPackingListInvoiceTabHandler;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RonmioPackingListInvoiceRepository extends MybatisplusPageRepository<RonmioPackingListInvoiceQueryDTO, RonmioPackingListInvoiceMapper, RonmioPackingListInvoiceModel> implements GenericPageRepository<RonmioPackingListInvoiceQueryDTO, RonmioPackingListInvoiceModel> {

    @Resource
    private List<RonmioPackingListInvoiceTabHandler> tabHandlers;

    @Override
    public List<RonmioPackingListInvoiceTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<RonmioPackingListInvoiceModel> fillQueryWrapper(RonmioPackingListInvoiceQueryDTO dto, IQueryWrapper<RonmioPackingListInvoiceModel> wrapper) {
        return wrapper;
    }

}
