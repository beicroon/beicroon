package com.beicroon.project.ronmio.dao.repository;

import com.beicroon.construct.database.repository.GenericPageRepository;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.project.ronmio.dao.mapper.RonmioCustomerMapper;
import com.beicroon.project.ronmio.dao.model.RonmioCustomerModel;
import com.beicroon.project.ronmio.dao.tab.RonmioCustomerTabHandler;
import com.beicroon.project.ronmio.entity.ronmiocustomer.dto.RonmioCustomerQueryDTO;
import com.beicroon.provider.mybatisplus.repository.MybatisplusPageRepository;
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
