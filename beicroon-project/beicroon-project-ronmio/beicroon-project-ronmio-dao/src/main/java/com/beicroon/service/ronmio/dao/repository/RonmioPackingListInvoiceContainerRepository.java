package com.beicroon.service.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.ronmio.dao.mapper.RonmioPackingListInvoiceContainerMapper;
import com.beicroon.service.ronmio.dao.model.RonmioPackingListInvoiceContainerModel;
import org.springframework.stereotype.Repository;

@Repository
public class RonmioPackingListInvoiceContainerRepository extends MybatisplusRepository<RonmioPackingListInvoiceContainerMapper, RonmioPackingListInvoiceContainerModel> implements GenericRepository<RonmioPackingListInvoiceContainerModel> {

}
