package com.beicroon.service.admin.dao.manager;

import com.beicroon.service.admin.dao.convertor.AccountAdminConvertor;
import com.beicroon.service.admin.dao.repository.AccountAdminRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountAdminManager {

     @Resource
     private AccountAdminConvertor accountAdminConvertor;

     @Resource
     private AccountAdminRepository accountAdminRepository;

}
