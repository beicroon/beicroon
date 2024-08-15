package com.beicroon.service.admin.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.service.admin.dao.mapper.ResourceMenuMapper;
import com.beicroon.service.admin.dao.model.ResourceMenuModel;
import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuQueryDTO;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import org.springframework.stereotype.Component;

@Component
public class ResourceMenuRepository extends GenericRepository<ResourceMenuQueryDTO, ResourceMenuMapper, ResourceMenuModel> implements IGenericRepository<ResourceMenuQueryDTO, ResourceMenuModel> {

    @Override
    public void fillQueryWrapper(ResourceMenuQueryDTO dto, QueryWrapper<ResourceMenuModel> wrapper) {
        LambdaQueryWrapper<ResourceMenuModel> query = wrapper.lambda();
    }

}
