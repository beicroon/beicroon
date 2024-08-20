package com.beicroon.starter.web.admin.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuQueryDTO;
import com.beicroon.starter.web.admin.mapper.ResourceMenuMapper;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
import org.springframework.stereotype.Component;

@Component
public class ResourceMenuRepository extends GenericRepository<ResourceMenuQueryDTO, ResourceMenuMapper, ResourceMenuModel> implements IGenericRepository<ResourceMenuQueryDTO, ResourceMenuModel> {

    @Override
    public void fillQueryWrapper(ResourceMenuQueryDTO dto, QueryWrapper<ResourceMenuModel> wrapper) {
        LambdaQueryWrapper<ResourceMenuModel> query = wrapper.lambda();
    }

}
