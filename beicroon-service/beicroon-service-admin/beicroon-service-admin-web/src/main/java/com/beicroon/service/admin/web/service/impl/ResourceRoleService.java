package com.beicroon.service.admin.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.dao.convertor.ResourceRoleConvertor;
import com.beicroon.service.admin.dao.model.ResourceRoleModel;
import com.beicroon.service.admin.dao.repository.ResourceRoleRepository;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleCreateDTO;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleQueryDTO;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleUpdateDTO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRoleBaseVO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRoleDetailVO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRolePageVO;
import com.beicroon.service.admin.web.service.IResourceRoleService;
import com.beicroon.starter.mysql.utils.PageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRoleService implements IResourceRoleService {

    @Resource
    private ResourceRoleConvertor resourceRoleConvertor;

    @Resource
    private ResourceRoleRepository resourceRoleRepository;

    @Override
    public ResourceRoleBaseVO show(Long id) {
        ResourceRoleModel resourceRole = this.resourceRoleRepository.getById(id);

        return this.resourceRoleConvertor.toBaseVO(resourceRole);
    }

    @Override
    public ResourceRoleDetailVO detail(Long id) {
        ResourceRoleModel resourceRole = this.resourceRoleRepository.getById(id);
        
        return this.resourceRoleConvertor.toDetailVO(resourceRole);
    }

    @Override
    public List<ResourceRoleBaseVO> list(ResourceRoleQueryDTO dto) {
        List<ResourceRoleModel> list = this.resourceRoleRepository.list(dto);
        
        return ListUtils.toList(list, this.resourceRoleConvertor::toBaseVO);
    }

    @Override
    public PageInfo<ResourceRolePageVO> page(ResourceRoleQueryDTO dto) {
        Page<ResourceRoleModel> page = this.resourceRoleRepository.page(dto);
        
        return PageUtils.result(page, this.resourceRoleConvertor::toPageVO);
    }

    @Override
    public boolean create(ResourceRoleCreateDTO dto) {
        ResourceRoleModel creator = this.resourceRoleConvertor.toEntity(dto);
        
        return this.resourceRoleRepository.save(creator);
    }

    @Override
    public boolean update(ResourceRoleUpdateDTO dto) {
        ResourceRoleModel updater = this.resourceRoleConvertor.toEntity(dto);
        
        return this.resourceRoleRepository.updateById(updater);
    }

    @Override
    public boolean remove(IdsDTO dto) {
        return this.resourceRoleRepository.removeByIds(dto.getIds());
    }

}
