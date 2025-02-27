package com.beicroon.service.admin.dao.manager;

import com.beicroon.service.admin.dao.convertor.AdminAccountConvertor;
import com.beicroon.service.admin.dao.repository.AdminAccountRepository;
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
