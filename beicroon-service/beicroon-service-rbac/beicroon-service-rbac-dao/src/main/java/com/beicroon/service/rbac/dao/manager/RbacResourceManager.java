package com.beicroon.service.rbac.dao.manager;

import com.beicroon.service.rbac.dao.convertor.RbacResourceConvertor;
import com.beicroon.service.rbac.dao.repository.RbacResourceRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RbacResourceManager {

    @Resource
    private RbacResourceConvertor rbacResourceConvertor;

    @Resource
    private RbacResourceRepository rbacResourceRepository;

}
