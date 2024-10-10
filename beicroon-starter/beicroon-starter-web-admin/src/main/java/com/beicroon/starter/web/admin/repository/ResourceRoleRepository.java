package com.beicroon.starter.web.admin.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleQueryDTO;
import com.beicroon.starter.web.admin.mapper.ResourceRoleMapper;
import com.beicroon.starter.web.admin.model.ResourceRoleModel;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceRoleRepository extends GenericRepository<ResourceRoleQueryDTO, ResourceRoleMapper, ResourceRoleModel> implements IGenericRepository<ResourceRoleQueryDTO, ResourceRoleModel> {

    @Override
    public void fillQueryWrapper(ResourceRoleQueryDTO dto, QueryWrapper<ResourceRoleModel> wrapper) {
        LambdaQueryWrapper<ResourceRoleModel> query = wrapper.lambda();
    }

}
