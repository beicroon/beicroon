package com.beicroon.service.rbac.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.rbac.dao.mapper.AdminRoleResourceMapper;
import com.beicroon.service.rbac.dao.model.AdminRoleResourceModel;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRoleResourceRepository extends MybatisplusRepository<AdminRoleResourceMapper, AdminRoleResourceModel> implements GenericRepository<AdminRoleResourceModel> {

}
