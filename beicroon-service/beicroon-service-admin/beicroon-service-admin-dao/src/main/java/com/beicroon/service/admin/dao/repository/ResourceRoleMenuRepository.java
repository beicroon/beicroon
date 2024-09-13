package com.beicroon.service.admin.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.service.admin.dao.mapper.ResourceRoleMenuMapper;
import com.beicroon.service.admin.dao.model.ResourceRoleMenuModel;
import com.beicroon.service.admin.entity.resource.role.menu.dto.ResourceRoleMenuQueryDTO;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import org.springframework.stereotype.Component;

@Component
public class ResourceRoleMenuRepository extends GenericRepository<ResourceRoleMenuQueryDTO, ResourceRoleMenuMapper, ResourceRoleMenuModel> implements IGenericRepository<ResourceRoleMenuQueryDTO, ResourceRoleMenuModel> {

    @Override
    public void fillQueryWrapper(ResourceRoleMenuQueryDTO dto, QueryWrapper<ResourceRoleMenuModel> wrapper) {
        LambdaQueryWrapper<ResourceRoleMenuModel> query = wrapper.lambda();
    }

}
