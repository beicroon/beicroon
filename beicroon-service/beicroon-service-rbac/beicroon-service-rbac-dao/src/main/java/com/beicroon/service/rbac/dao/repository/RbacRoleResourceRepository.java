package com.beicroon.service.rbac.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.rbac.dao.mapper.RbacRoleResourceMapper;
import com.beicroon.service.rbac.dao.model.RbacRoleResourceModel;
import org.springframework.stereotype.Repository;

@Repository
public class RbacRoleResourceRepository extends MybatisplusRepository<RbacRoleResourceMapper, RbacRoleResourceModel> implements GenericRepository<RbacRoleResourceModel> {

}
