package com.beicroon.service.admin.dao.manager;

import com.beicroon.service.admin.dao.convertor.AdminResourceConvertor;
import com.beicroon.service.admin.dao.repository.AdminResourceRepository;
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
