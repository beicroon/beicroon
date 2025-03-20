package com.beicroon.service.rbac.dao.manager;

import com.beicroon.service.rbac.dao.convertor.RbacAccountConvertor;
import com.beicroon.service.rbac.dao.repository.RbacAccountRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RbacAccountManager {

    @Resource
    private RbacAccountConvertor rbacAccountConvertor;

    @Resource
    private RbacAccountRepository rbacAccountRepository;

}
