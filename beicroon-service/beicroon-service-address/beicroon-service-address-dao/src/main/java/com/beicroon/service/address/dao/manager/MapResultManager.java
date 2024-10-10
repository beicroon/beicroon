package com.beicroon.service.address.dao.manager;

import com.beicroon.service.address.dao.convertor.MapResultConvertor;
import com.beicroon.service.address.dao.repository.MapResultRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MapResultManager {

     @Resource
     private MapResultConvertor mapResultConvertor;

     @Resource
     private MapResultRepository mapResultRepository;

}
