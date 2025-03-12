package com.beicroon.service.rbac.dao.manager;

import com.beicroon.service.rbac.dao.convertor.AdminAccountConvertor;
import com.beicroon.service.rbac.dao.repository.AdminAccountRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminAccountManager {

    @Resource
    private AdminAccountConvertor adminAccountConvertor;

    @Resource
    private AdminAccountRepository adminAccountRepository;

}
