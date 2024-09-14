package com.beicroon.starter.web.admin.repository;

import com.beicroon.starter.mysql.repository.ISuperRepository;
import com.beicroon.starter.mysql.repository.impl.SuperRepository;
import com.beicroon.starter.web.admin.mapper.AccountAdminRoleMapper;
import com.beicroon.starter.web.admin.model.AccountAdminRoleModel;
import org.springframework.stereotype.Component;

@Component
public class AccountAdminRoleRepository extends SuperRepository<AccountAdminRoleMapper, AccountAdminRoleModel> implements ISuperRepository<AccountAdminRoleModel> {

}
