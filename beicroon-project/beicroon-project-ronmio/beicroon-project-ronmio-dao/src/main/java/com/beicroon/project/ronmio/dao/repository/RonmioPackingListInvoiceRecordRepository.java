package com.beicroon.project.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.project.ronmio.dao.mapper.RonmioPackingListInvoiceRecordMapper;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceRecordModel;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RonmioPackingListInvoiceRecordRepository extends MybatisplusRepository<RonmioPackingListInvoiceRecordMapper, RonmioPackingListInvoiceRecordModel> implements GenericRepository<RonmioPackingListInvoiceRecordModel> {

}
