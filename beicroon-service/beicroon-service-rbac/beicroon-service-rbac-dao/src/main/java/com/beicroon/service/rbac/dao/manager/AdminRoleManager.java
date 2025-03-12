package com.beicroon.service.rbac.dao.manager;

import com.beicroon.service.rbac.dao.convertor.AdminRoleConvertor;
import com.beicroon.service.rbac.dao.repository.AdminRoleRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminRoleManager {

    @Resource
    private AdminRoleConvertor adminRoleConvertor;

    @Resource
    private AdminRoleRepository adminRoleRepository;

}
