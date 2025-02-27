package com.beicroon.service.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.ronmio.dao.mapper.RonmioPackingListInvoicePriceMapper;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoicePriceModel;
import org.springframework.stereotype.Repository;

@Repository
public class RonmioPackingListInvoicePriceRepository extends MybatisplusRepository<RonmioPackingListInvoicePriceMapper, RonmioPackingListInvoicePriceModel> implements GenericRepository<RonmioPackingListInvoicePriceModel> {

}
