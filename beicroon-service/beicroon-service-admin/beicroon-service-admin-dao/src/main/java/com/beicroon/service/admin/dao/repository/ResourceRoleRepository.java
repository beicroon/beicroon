package com.beicroon.service.admin.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.service.admin.dao.mapper.ResourceRoleMapper;
import com.beicroon.service.admin.dao.model.ResourceRoleModel;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleQueryDTO;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import org.springframework.stereotype.Component;

@Component
public class ResourceRoleRepository extends GenericRepository<ResourceRoleQueryDTO, ResourceRoleMapper, ResourceRoleModel> implements IGenericRepository<ResourceRoleQueryDTO, ResourceRoleModel> {

    @Override
    public void fillQueryWrapper(ResourceRoleQueryDTO dto, QueryWrapper<ResourceRoleModel> wrapper) {
        LambdaQueryWrapper<ResourceRoleModel> query = wrapper.lambda();
    }

}
