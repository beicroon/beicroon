package com.beicroon.starter.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.mysql.utils.PageUtils;
import com.beicroon.starter.web.admin.convertor.ResourceRoleConvertor;
import com.beicroon.starter.web.admin.convertor.ResourceRoleMenuConvertor;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleQueryDTO;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.menu.dto.ResourceRoleMenuAssignDTO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleBaseVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleDetailVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRolePageVO;
import com.beicroon.starter.web.admin.helper.ResourceRoleHelper;
import com.beicroon.starter.web.admin.manager.ResourceRoleManager;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
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
    private ResourceRoleConvertor resourceRoleConvertor;

    @Resource
    private ResourceRoleMenuConvertor resourceRoleMenuConvertor;

    @Resource
    private ResourceRoleRepository resourceRoleRepository;

    @Resource
    private ResourceMenuRepository resourceMenuRepository;

    @Resource
    private ResourceRoleMenuRepository resourceRoleMenuRepository;

    @Resource
    private ResourceRoleManager resourceRoleManager;

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
        QueryWrapper<ResourceRoleModel> wrapper = this.resourceRoleRepository.newPageWrapper(dto);

        List<ResourceRoleModel> roles = this.resourceRoleManager.listAuthUserRole();

        if (ResourceRoleHelper.isNotRoot(roles)) {
            wrapper.lambda().eq(ResourceRoleModel::getRootFlag, BooleanEnums.FALSE.getCode());
        }

        List<ResourceRoleModel> list = this.resourceRoleRepository.list(wrapper);

        return ListUtils.toList(list, this.resourceRoleConvertor::toBaseVO);
    }

    @Override
    public PageInfo<ResourceRolePageVO> page(ResourceRoleQueryDTO dto) {
        Page<ResourceRoleModel> page = this.resourceRoleRepository.page(dto);

        return PageUtils.result(page, this.resourceRoleConvertor::toPageVO);
    }

    @Override
    public boolean create(ResourceRoleCreateDTO dto) {
        if (this.resourceRoleRepository.existed(ResourceRoleModel::getName, dto.getName())) {
            throw ExceptionUtils.business("角色名称已存在");
        }

        ResourceRoleModel creator = this.resourceRoleConvertor.toEntity(dto);

        return this.resourceRoleRepository.save(creator);
    }

    @Override
    public boolean update(ResourceRoleUpdateDTO dto) {
        ResourceRoleModel role = this.resourceRoleRepository.getByIdOrError(dto.getId(), "角色不存在或已删除");

        if (ResourceRoleHelper.isRoot(role)) {
            throw ExceptionUtils.business("超级管理员角色无法修改");
        }

        if (this.resourceRoleRepository.existed(ResourceRoleModel::getName, dto.getName(), role.getId())) {
            throw ExceptionUtils.business("角色名称已存在");
        }

        ResourceRoleModel updater = this.resourceRoleConvertor.toEntity(dto);

        return this.resourceRoleRepository.updateById(updater);
    }

    @Override
    public boolean remove(IdsDTO dto) {
        return this.resourceRoleRepository.removeByIds(dto.getIds());
    }

    @Override
    public List<Long> menuIdList(Long roleId) {
        ResourceRoleModel role = this.resourceRoleRepository.getByIdOrError(roleId, "角色不存在或已删除");

        Set<Long> menuIds;

        if (ResourceRoleHelper.isRoot(role)) {
            menuIds = this.resourceMenuRepository.listEnable(ResourceMenuModel::getId);
        } else {
            menuIds = this.resourceRoleMenuRepository.list(
                    ResourceRoleMenuModel::getRoleId, roleId, ResourceRoleMenuModel::getMenuId
            );
        }

        return new ArrayList<>(menuIds);
    }

    @Override
    public boolean menuAssign(ResourceRoleMenuAssignDTO dto) {
        ResourceRoleModel role = this.resourceRoleRepository.getByIdOrError(dto.getRoleId(), "角色不存在或已删除");

        if (ResourceRoleHelper.isRoot(role)) {
            throw ExceptionUtils.business("超级管理员自动拥有所有菜单权限~无需手动分配！");
        }

        this.resourceRoleMenuRepository.remove(ResourceRoleMenuModel::getRoleId, dto.getRoleId());

        return this.resourceRoleMenuRepository.saveBatch(this.resourceRoleMenuConvertor.toEntity(dto));
    }

}
