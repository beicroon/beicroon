package com.beicroon.starter.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.mysql.utils.PageUtils;
import com.beicroon.starter.web.admin.convertor.ResourceMenuConvertor;
import com.beicroon.starter.web.admin.convertor.ResourceRoleConvertor;
import com.beicroon.starter.web.admin.convertor.ResourceRoleMenuConvertor;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleQueryDTO;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.menu.dto.ResourceRoleMenuAssignDTO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleBaseVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleDetailVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRolePageVO;
import com.beicroon.starter.web.admin.model.ResourceRoleMenuModel;
import com.beicroon.starter.web.admin.model.ResourceRoleModel;
import com.beicroon.starter.web.admin.repository.ResourceMenuRepository;
import com.beicroon.starter.web.admin.repository.ResourceRoleMenuRepository;
import com.beicroon.starter.web.admin.repository.ResourceRoleRepository;
import com.beicroon.starter.web.admin.service.IResourceRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ResourceRoleService implements IResourceRoleService {

    @Resource
    private ResourceMenuConvertor resourceMenuConvertor;

    @Resource
    private ResourceRoleConvertor resourceRoleConvertor;

    @Resource
    private ResourceRoleMenuConvertor resourceRoleMenuConvertor;

    @Resource
    private ResourceMenuRepository resourceMenuRepository;

    @Resource
    private ResourceRoleRepository resourceRoleRepository;

    @Resource
    private ResourceRoleMenuRepository resourceRoleMenuRepository;

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

    @Override
    public List<Long> menuIdList(Long roleId) {
        Set<Long> menuIds = this.resourceRoleMenuRepository.list(
                ResourceRoleMenuModel::getRoleId, roleId, ResourceRoleMenuModel::getMenuId
        );

        return new ArrayList<>(menuIds);
    }

    @Override
    public boolean menuAssign(ResourceRoleMenuAssignDTO dto) {
        this.resourceRoleMenuRepository.remove(ResourceRoleMenuModel::getRoleId, dto.getRoleId());

        return this.resourceRoleMenuRepository.saveBatch(this.resourceRoleMenuConvertor.toEntity(dto));
    }

}
