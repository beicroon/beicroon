package com.beicroon.service.admin.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.service.admin.app.service.IAdminRoleService;
import com.beicroon.service.admin.dao.convertor.AdminRoleConvertor;
import com.beicroon.service.admin.dao.manager.AdminRoleManager;
import com.beicroon.service.admin.dao.model.AdminRoleModel;
import com.beicroon.service.admin.dao.model.AdminRoleResourceModel;
import com.beicroon.service.admin.dao.repository.AdminRoleRepository;
import com.beicroon.service.admin.dao.repository.AdminRoleResourceRepository;
import com.beicroon.service.admin.entity.adminrole.dto.AdminRoleCreateDTO;
import com.beicroon.service.admin.entity.adminrole.dto.AdminRoleQueryDTO;
import com.beicroon.service.admin.entity.adminrole.dto.AdminRoleUpdateDTO;
import com.beicroon.service.admin.entity.adminrole.vo.AdminRoleBaseVO;
import com.beicroon.service.admin.entity.adminrole.vo.AdminRoleDetailVO;
import com.beicroon.service.admin.entity.adminrole.vo.AdminRolePageVO;
import com.beicroon.starter.database.manager.DBManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminRoleService implements IAdminRoleService {

    @Resource
    private DBManager dbManager;

    @Resource
    private AdminRoleManager adminRoleManager;

    @Resource
    private AdminRoleConvertor adminRoleConvertor;

    @Resource
    private AdminRoleRepository adminRoleRepository;

    @Resource
    private AdminRoleResourceRepository adminRoleResourceRepository;

    @Override
    public List<TabVO> tab(AdminRoleQueryDTO dto) {
        return this.adminRoleRepository.tab(dto);
    }

    @Override
    public List<AdminRoleBaseVO> list(AdminRoleQueryDTO dto) {
        List<AdminRoleModel> list = this.adminRoleRepository.list(dto);

        return this.adminRoleConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<AdminRolePageVO> page(AdminRoleQueryDTO dto) {
        PageInfo<AdminRoleModel> page = this.adminRoleRepository.page(dto);

        return this.adminRoleConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(AdminRoleCreateDTO dto) {
        if (this.adminRoleRepository.existed(AdminRoleModel::getCode, dto.getCode())) {
            throw ExceptionUtils.business("角色编码已存在");
        }

        if (this.adminRoleRepository.existed(AdminRoleModel::getName, dto.getName())) {
            throw ExceptionUtils.business("角色名称已存在");
        }

        AdminRoleModel model = this.adminRoleConvertor.toCreator(dto);

        List<AdminRoleResourceModel> resources = this.adminRoleConvertor.toResourceModel(model, dto.getResourceIds());

        return this.dbManager.execute(() -> {
            this.adminRoleRepository.insert(model);

            this.adminRoleResourceRepository.insert(resources);
        });
    }

    @Override
    public Boolean update(AdminRoleUpdateDTO dto) {
        if (this.adminRoleRepository.existed(AdminRoleModel::getCode, dto.getCode(), dto.getId())) {
            throw ExceptionUtils.business("角色编码已存在");
        }

        if (this.adminRoleRepository.existed(AdminRoleModel::getName, dto.getName(), dto.getId())) {
            throw ExceptionUtils.business("角色名称已存在");
        }

        AdminRoleModel model = this.adminRoleConvertor.toUpdater(dto);

        List<AdminRoleResourceModel> resources = this.adminRoleConvertor.toResourceModel(model, dto.getResourceIds());

        return this.dbManager.execute(() -> {
            this.adminRoleRepository.updateById(model);

            this.adminRoleResourceRepository.delete(AdminRoleResourceModel::getRoleId, model.getId());

            this.adminRoleResourceRepository.insert(resources);
        });
    }

    @Override
    public AdminRoleBaseVO show(Long id) {
        AdminRoleModel model = this.adminRoleRepository.getById(id);

        return this.adminRoleConvertor.toBaseVO(model);
    }

    @Override
    public AdminRoleDetailVO detail(Long id) {
        AdminRoleModel model = this.adminRoleRepository.getById(id);

        Set<Long> resourceIds = this.adminRoleResourceRepository.list(
                AdminRoleResourceModel::getRoleId, model.getId(), AdminRoleResourceModel::getResourceId
        );

        return this.adminRoleConvertor.toDetailVO(
                model, vo -> vo.setResourceIds(new ArrayList<>(resourceIds))
        );
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        List<AdminRoleModel> models = this.adminRoleRepository.listByIds(dto.getIds());

        for (AdminRoleModel model : models) {
            if (BooleanEnums.isTrue(model.getSystemFlag())) {
                throw ExceptionUtils.business(String.format("角色[%s]不允许删除", model.getName()));
            }
        }

        return this.adminRoleRepository.removeByIds(dto.getIds());
    }

}
