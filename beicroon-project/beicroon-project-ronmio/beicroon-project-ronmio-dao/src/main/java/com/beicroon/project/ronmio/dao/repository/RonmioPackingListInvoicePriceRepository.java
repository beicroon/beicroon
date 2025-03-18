package com.beicroon.project.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.project.ronmio.dao.mapper.RonmioPackingListInvoicePriceMapper;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoicePriceModel;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RonmioPackingListInvoicePriceRepository extends MybatisplusRepository<RonmioPackingListInvoicePriceMapper, RonmioPackingListInvoicePriceModel> implements GenericRepository<RonmioPackingListInvoicePriceModel> {

}
