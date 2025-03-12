package com.beicroon.service.rbac.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.rbac.dao.mapper.AdminAccountRoleMapper;
import com.beicroon.service.rbac.dao.model.AdminAccountRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public class AdminAccountRoleRepository extends MybatisplusRepository<AdminAccountRoleMapper, AdminAccountRoleModel> implements GenericRepository<AdminAccountRoleModel> {

}
