package com.beicroon.service.rbac.dao.repository;

import com.beicroon.construct.database.repository.GenericRepository;
import com.beicroon.provider.mybatisplus.repository.MybatisplusRepository;
import com.beicroon.service.rbac.dao.mapper.RbacAccountRoleMapper;
import com.beicroon.service.rbac.dao.model.RbacAccountRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public class RbacAccountRoleRepository extends MybatisplusRepository<RbacAccountRoleMapper, RbacAccountRoleModel> implements GenericRepository<RbacAccountRoleModel> {

}
