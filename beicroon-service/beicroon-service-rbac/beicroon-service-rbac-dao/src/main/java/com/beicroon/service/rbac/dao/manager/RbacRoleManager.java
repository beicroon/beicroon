package com.beicroon.service.rbac.dao.manager;

import com.beicroon.service.rbac.dao.convertor.RbacRoleConvertor;
import com.beicroon.service.rbac.dao.repository.RbacRoleRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RbacRoleManager {

    @Resource
    private RbacRoleConvertor rbacRoleConvertor;

    @Resource
    private RbacRoleRepository rbacRoleRepository;

}
