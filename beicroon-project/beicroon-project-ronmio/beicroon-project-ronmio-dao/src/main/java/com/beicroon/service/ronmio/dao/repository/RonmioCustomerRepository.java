package com.beicroon.service.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
import com.beicroon.service.ronmio.dao.mapper.RonmioCustomerMapper;
import com.beicroon.service.ronmio.dao.model.RonmioCustomerModel;
import com.beicroon.service.ronmio.dao.tab.RonmioCustomerTabHandler;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerQueryDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RonmioCustomerRepository extends MybatisplusPageRepository<RonmioCustomerQueryDTO, RonmioCustomerMapper, RonmioCustomerModel> implements GenericPageRepository<RonmioCustomerQueryDTO, RonmioCustomerModel> {

    @Resource
    private List<RonmioCustomerTabHandler> tabHandlers;

    @Override
    public List<RonmioCustomerTabHandler> getTabHandlers() {
        return this.tabHandlers;
    }

    @Override
    public IQueryWrapper<RonmioCustomerModel> fillQueryWrapper(RonmioCustomerQueryDTO dto, IQueryWrapper<RonmioCustomerModel> wrapper) {
        return wrapper;
    }

}
