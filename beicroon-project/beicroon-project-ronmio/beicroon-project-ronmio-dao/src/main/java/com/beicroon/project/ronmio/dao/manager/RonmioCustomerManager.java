package com.beicroon.project.ronmio.dao.manager;

import com.beicroon.project.ronmio.dao.convertor.RonmioCustomerConvertor;
import com.beicroon.project.ronmio.dao.repository.RonmioCustomerRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RonmioCustomerManager {

    @Resource
    private RonmioCustomerConvertor ronmioCustomerConvertor;

    @Resource
    private RonmioCustomerRepository ronmioCustomerRepository;

}
