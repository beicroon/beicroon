package com.beicroon.starter.web.admin.manager;

import com.beicroon.starter.web.admin.convertor.AccountAdminConvertor;
import com.beicroon.starter.web.admin.repository.AccountAdminRepository;
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
