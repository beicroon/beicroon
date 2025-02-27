package com.beicroon.service.admin.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.admin.dao.mapper.AdminAccountRoleMapper;
import com.beicroon.service.admin.dao.model.AdminAccountRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public class AdminAccountRoleRepository extends MybatisplusRepository<AdminAccountRoleMapper, AdminAccountRoleModel> implements GenericRepository<AdminAccountRoleModel> {

}
