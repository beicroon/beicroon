package com.beicroon.starter.web.admin.manager;

import com.beicroon.starter.web.admin.convertor.ResourceMenuConvertor;
import com.beicroon.starter.web.admin.repository.ResourceMenuRepository;
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
