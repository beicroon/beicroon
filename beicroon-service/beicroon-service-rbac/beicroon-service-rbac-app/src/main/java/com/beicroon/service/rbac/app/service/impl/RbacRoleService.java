package com.beicroon.service.rbac.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.service.rbac.app.service.IRbacRoleService;
import com.beicroon.service.rbac.dao.convertor.RbacRoleConvertor;
import com.beicroon.service.rbac.dao.manager.RbacRoleManager;
import com.beicroon.service.rbac.dao.model.RbacRoleModel;
import com.beicroon.service.rbac.dao.model.RbacRoleResourceModel;
import com.beicroon.service.rbac.dao.repository.RbacRoleRepository;
import com.beicroon.service.rbac.dao.repository.RbacRoleResourceRepository;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleCreateDTO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleQueryDTO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleUpdateDTO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleBaseVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleDetailVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRolePageVO;
import com.beicroon.starter.database.manager.DBManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RbacRoleService implements IRbacRoleService {

    @Resource
    private DBManager dbManager;

    @Resource
    private RbacRoleManager rbacRoleManager;

    @Resource
    private RbacRoleConvertor rbacRoleConvertor;

    @Resource
    private RbacRoleRepository rbacRoleRepository;

    @Resource
    private RbacRoleResourceRepository rbacRoleResourceRepository;

    @Override
    public List<TabVO> tab(RbacRoleQueryDTO dto) {
        return this.rbacRoleRepository.tab(dto);
    }

    @Override
    public List<RbacRoleBaseVO> list(RbacRoleQueryDTO dto) {
        List<RbacRoleModel> list = this.rbacRoleRepository.list(dto);

        return this.rbacRoleConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<RbacRolePageVO> page(RbacRoleQueryDTO dto) {
        PageInfo<RbacRoleModel> page = this.rbacRoleRepository.page(dto);

        return this.rbacRoleConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(RbacRoleCreateDTO dto) {
        if (this.rbacRoleRepository.existed(RbacRoleModel::getCode, dto.getCode())) {
            throw ExceptionUtils.business("角色编码已存在");
        }

        if (this.rbacRoleRepository.existed(RbacRoleModel::getName, dto.getName())) {
            throw ExceptionUtils.business("角色名称已存在");
        }

        RbacRoleModel model = this.rbacRoleConvertor.toCreator(dto);

        List<RbacRoleResourceModel> resources = this.rbacRoleConvertor.toResourceModel(model, dto.getResourceIds());

        return this.dbManager.execute(() -> {
            this.rbacRoleRepository.insert(model);

            this.rbacRoleResourceRepository.insert(resources);
        });
    }

    @Override
    public Boolean update(RbacRoleUpdateDTO dto) {
        if (this.rbacRoleRepository.existed(RbacRoleModel::getCode, dto.getCode(), dto.getId())) {
            throw ExceptionUtils.business("角色编码已存在");
        }

        if (this.rbacRoleRepository.existed(RbacRoleModel::getName, dto.getName(), dto.getId())) {
            throw ExceptionUtils.business("角色名称已存在");
        }

        RbacRoleModel model = this.rbacRoleConvertor.toUpdater(dto);

        List<RbacRoleResourceModel> resources = this.rbacRoleConvertor.toResourceModel(model, dto.getResourceIds());

        return this.dbManager.execute(() -> {
            this.rbacRoleRepository.updateById(model);

            this.rbacRoleResourceRepository.delete(RbacRoleResourceModel::getRoleId, model.getId());

            this.rbacRoleResourceRepository.insert(resources);
        });
    }

    @Override
    public RbacRoleBaseVO show(Long id) {
        RbacRoleModel model = this.rbacRoleRepository.getById(id);

        return this.rbacRoleConvertor.toBaseVO(model);
    }

    @Override
    public RbacRoleDetailVO detail(Long id) {
        RbacRoleModel model = this.rbacRoleRepository.getById(id);

        Set<Long> resourceIds = this.rbacRoleResourceRepository.list(
                RbacRoleResourceModel::getRoleId, model.getId(), RbacRoleResourceModel::getResourceId
        );

        return this.rbacRoleConvertor.toDetailVO(
                model, vo -> vo.setResourceIds(new ArrayList<>(resourceIds))
        );
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        List<RbacRoleModel> models = this.rbacRoleRepository.listByIds(dto.getIds());

        for (RbacRoleModel model : models) {
            if (BooleanEnums.isTrue(model.getSystemFlag())) {
                throw ExceptionUtils.business(String.format("角色[%s]不允许删除", model.getName()));
            }
        }

        return this.rbacRoleRepository.removeByIds(dto.getIds());
    }

}
