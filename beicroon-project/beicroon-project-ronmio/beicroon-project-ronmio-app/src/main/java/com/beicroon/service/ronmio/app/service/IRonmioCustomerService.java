package com.beicroon.service.ronmio.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerCreateDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerQueryDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerUpdateDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerBaseVO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerDetailVO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerPageVO;

import java.util.List;

public interface IRonmioCustomerService {

    List<TabVO> tab(RonmioCustomerQueryDTO dto);

    List<RonmioCustomerBaseVO> list(RonmioCustomerQueryDTO dto);

    PageInfo<RonmioCustomerPageVO> page(RonmioCustomerQueryDTO dto);

    Boolean create(RonmioCustomerCreateDTO dto);

    Boolean update(RonmioCustomerUpdateDTO dto);

    RonmioCustomerBaseVO show(Long id);

    RonmioCustomerDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
