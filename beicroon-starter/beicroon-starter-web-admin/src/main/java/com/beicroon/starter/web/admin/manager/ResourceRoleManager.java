package com.beicroon.starter.web.admin.manager;

import com.beicroon.starter.web.admin.convertor.ResourceRoleConvertor;
import com.beicroon.starter.web.admin.repository.ResourceRoleRepository;
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
