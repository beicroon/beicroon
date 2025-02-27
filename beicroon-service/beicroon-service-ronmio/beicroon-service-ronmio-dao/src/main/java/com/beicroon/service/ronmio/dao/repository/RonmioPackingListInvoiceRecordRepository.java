package com.beicroon.service.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.ronmio.dao.mapper.RonmioPackingListInvoiceRecordMapper;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoiceRecordModel;
import org.springframework.stereotype.Repository;

@Repository
public class RonmioPackingListInvoiceRecordRepository extends MybatisplusRepository<RonmioPackingListInvoiceRecordMapper, RonmioPackingListInvoiceRecordModel> implements GenericRepository<RonmioPackingListInvoiceRecordModel> {

}
