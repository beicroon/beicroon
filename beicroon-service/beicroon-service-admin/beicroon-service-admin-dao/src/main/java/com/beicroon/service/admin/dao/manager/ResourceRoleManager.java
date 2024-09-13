package com.beicroon.service.admin.dao.manager;

import com.beicroon.service.admin.dao.convertor.ResourceRoleConvertor;
import com.beicroon.service.admin.dao.repository.ResourceRoleRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResourceRoleManager {

     @Resource
     private ResourceRoleConvertor resourceRoleConvertor;

     @Resource
     private ResourceRoleRepository resourceRoleRepository;

}
