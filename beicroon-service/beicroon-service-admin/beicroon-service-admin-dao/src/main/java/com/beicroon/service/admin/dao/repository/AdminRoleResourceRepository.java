package com.beicroon.service.admin.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.admin.dao.mapper.AdminRoleResourceMapper;
import com.beicroon.service.admin.dao.model.AdminRoleResourceModel;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRoleResourceRepository extends MybatisplusRepository<AdminRoleResourceMapper, AdminRoleResourceModel> implements GenericRepository<AdminRoleResourceModel> {

}
