package com.beicroon.service.admin.dao.manager;

import com.beicroon.service.admin.dao.convertor.ResourceMenuConvertor;
import com.beicroon.service.admin.dao.repository.ResourceMenuRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResourceMenuManager {

     @Resource
     private ResourceMenuConvertor resourceMenuConvertor;

     @Resource
     private ResourceMenuRepository resourceMenuRepository;

}
