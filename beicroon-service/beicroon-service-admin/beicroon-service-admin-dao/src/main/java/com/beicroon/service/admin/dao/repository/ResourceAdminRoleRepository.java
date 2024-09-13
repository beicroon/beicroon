package com.beicroon.service.admin.dao.repository;

import com.beicroon.service.admin.dao.mapper.ResourceAdminRoleMapper;
import com.beicroon.service.admin.dao.model.ResourceAdminRoleModel;
import com.beicroon.starter.mysql.repository.ISuperRepository;
import com.beicroon.starter.mysql.repository.impl.SuperRepository;
import org.springframework.stereotype.Component;

@Component
public class ResourceAdminRoleRepository extends SuperRepository<ResourceAdminRoleMapper, ResourceAdminRoleModel> implements ISuperRepository<ResourceAdminRoleModel> {

}
