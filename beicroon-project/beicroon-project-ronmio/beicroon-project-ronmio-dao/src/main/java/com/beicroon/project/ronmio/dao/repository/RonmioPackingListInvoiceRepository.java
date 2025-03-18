package com.beicroon.project.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.project.ronmio.dao.mapper.RonmioPackingListInvoiceMapper;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceModel;
import com.beicroon.project.ronmio.dao.tab.RonmioPackingListInvoiceTabHandler;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceQueryDTO;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
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
