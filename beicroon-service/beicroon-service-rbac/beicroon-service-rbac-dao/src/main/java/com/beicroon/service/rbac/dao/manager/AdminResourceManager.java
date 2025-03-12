package com.beicroon.service.rbac.dao.manager;

import com.beicroon.service.rbac.dao.convertor.AdminResourceConvertor;
import com.beicroon.service.rbac.dao.repository.AdminResourceRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminResourceManager {

    @Resource
    private AdminResourceConvertor adminResourceConvertor;

    @Resource
    private AdminResourceRepository adminResourceRepository;

}
