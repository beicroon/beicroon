package com.beicroon.project.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.project.ronmio.dao.mapper.RonmioPackingListInvoiceContainerMapper;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceContainerModel;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RonmioPackingListInvoiceContainerRepository extends MybatisplusRepository<RonmioPackingListInvoiceContainerMapper, RonmioPackingListInvoiceContainerModel> implements GenericRepository<RonmioPackingListInvoiceContainerModel> {

}
