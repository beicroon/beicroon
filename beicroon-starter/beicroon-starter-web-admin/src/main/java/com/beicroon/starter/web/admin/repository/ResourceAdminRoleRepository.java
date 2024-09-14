package com.beicroon.starter.web.admin.repository;

import com.beicroon.starter.mysql.repository.ISuperRepository;
import com.beicroon.starter.mysql.repository.impl.SuperRepository;
import com.beicroon.starter.web.admin.mapper.ResourceAdminRoleMapper;
import com.beicroon.starter.web.admin.model.ResourceAdminRoleModel;
import org.springframework.stereotype.Component;

@Component
public class ResourceAdminRoleRepository extends SuperRepository<ResourceAdminRoleMapper, ResourceAdminRoleModel> implements ISuperRepository<ResourceAdminRoleModel> {

}
