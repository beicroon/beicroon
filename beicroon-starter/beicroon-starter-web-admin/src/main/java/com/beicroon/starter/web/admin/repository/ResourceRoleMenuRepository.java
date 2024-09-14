package com.beicroon.starter.web.admin.repository;

import com.beicroon.starter.mysql.repository.ISuperRepository;
import com.beicroon.starter.mysql.repository.impl.SuperRepository;
import com.beicroon.starter.web.admin.mapper.ResourceRoleMenuMapper;
import com.beicroon.starter.web.admin.model.ResourceRoleMenuModel;
import org.springframework.stereotype.Component;

@Component
public class ResourceRoleMenuRepository extends SuperRepository<ResourceRoleMenuMapper, ResourceRoleMenuModel> implements ISuperRepository<ResourceRoleMenuModel> {

}
