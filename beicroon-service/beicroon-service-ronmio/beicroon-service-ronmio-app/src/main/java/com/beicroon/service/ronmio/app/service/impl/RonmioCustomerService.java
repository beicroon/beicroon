package com.beicroon.service.ronmio.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.ronmio.app.service.IRonmioCustomerService;
import com.beicroon.service.ronmio.dao.convertor.RonmioCustomerConvertor;
import com.beicroon.service.ronmio.dao.manager.RonmioCustomerManager;
import com.beicroon.service.ronmio.dao.model.RonmioCustomerModel;
import com.beicroon.service.ronmio.dao.repository.RonmioCustomerRepository;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerCreateDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerQueryDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerUpdateDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerBaseVO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerDetailVO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerPageVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RonmioCustomerService implements IRonmioCustomerService {

    @Resource
    private RonmioCustomerManager ronmioCustomerManager;

    @Resource
    private RonmioCustomerConvertor ronmioCustomerConvertor;

    @Resource
    private RonmioCustomerRepository ronmioCustomerRepository;

    @Override
    public List<TabVO> tab(RonmioCustomerQueryDTO dto) {
        return this.ronmioCustomerRepository.tab(dto);
    }

    @Override
    public List<RonmioCustomerBaseVO> list(RonmioCustomerQueryDTO dto) {
        List<RonmioCustomerModel> list = this.ronmioCustomerRepository.list(dto);

        return this.ronmioCustomerConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<RonmioCustomerPageVO> page(RonmioCustomerQueryDTO dto) {
        PageInfo<RonmioCustomerModel> page = this.ronmioCustomerRepository.page(dto);

        return this.ronmioCustomerConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(RonmioCustomerCreateDTO dto) {
        RonmioCustomerModel model = this.ronmioCustomerConvertor.toCreator(dto);

        return this.ronmioCustomerRepository.insert(model);
    }

    @Override
    public Boolean update(RonmioCustomerUpdateDTO dto) {
        RonmioCustomerModel model = this.ronmioCustomerConvertor.toUpdater(dto);

        return this.ronmioCustomerRepository.updateById(model);
    }

    @Override
    public RonmioCustomerBaseVO show(Long id) {
        RonmioCustomerModel model = this.ronmioCustomerRepository.getById(id);

        return this.ronmioCustomerConvertor.toBaseVO(model);
    }

    @Override
    public RonmioCustomerDetailVO detail(Long id) {
        RonmioCustomerModel model = this.ronmioCustomerRepository.getById(id);

        return this.ronmioCustomerConvertor.toDetailVO(model);
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        return this.ronmioCustomerRepository.removeByIds(dto.getIds());
    }

}
