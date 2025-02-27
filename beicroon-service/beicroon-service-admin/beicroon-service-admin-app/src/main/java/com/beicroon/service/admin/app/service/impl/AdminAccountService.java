package com.beicroon.service.admin.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.service.admin.app.service.IAdminAccountService;
import com.beicroon.service.admin.dao.convertor.AdminAccountConvertor;
import com.beicroon.service.admin.dao.helper.AdminAccountHelper;
import com.beicroon.service.admin.dao.manager.AdminAccountManager;
import com.beicroon.service.admin.dao.model.AdminAccountModel;
import com.beicroon.service.admin.dao.model.AdminAccountRoleModel;
import com.beicroon.service.admin.dao.repository.AdminAccountRepository;
import com.beicroon.service.admin.dao.repository.AdminAccountRoleRepository;
import com.beicroon.service.admin.entity.adminaccount.dto.AdminAccountCreateDTO;
import com.beicroon.service.admin.entity.adminaccount.dto.AdminAccountQueryDTO;
import com.beicroon.service.admin.entity.adminaccount.dto.AdminAccountUpdateDTO;
import com.beicroon.service.admin.entity.adminaccount.vo.AdminAccountBaseVO;
import com.beicroon.service.admin.entity.adminaccount.vo.AdminAccountDetailVO;
import com.beicroon.service.admin.entity.adminaccount.vo.AdminAccountPageVO;
import com.beicroon.starter.database.manager.DBManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminAccountService implements IAdminAccountService {

    @Resource
    private DBManager dbManager;

    @Resource
    private AdminAccountManager adminAccountManager;

    @Resource
    private AdminAccountConvertor adminAccountConvertor;

    @Resource
    private AdminAccountRepository adminAccountRepository;

    @Resource
    private AdminAccountRoleRepository adminAccountRoleRepository;

    @Override
    public List<TabVO> tab(AdminAccountQueryDTO dto) {
        return this.adminAccountRepository.tab(dto);
    }

    @Override
    public List<AdminAccountBaseVO> list(AdminAccountQueryDTO dto) {
        List<AdminAccountModel> list = this.adminAccountRepository.list(dto);

        return this.adminAccountConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<AdminAccountPageVO> page(AdminAccountQueryDTO dto) {
        PageInfo<AdminAccountModel> page = this.adminAccountRepository.page(dto);

        return this.adminAccountConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(AdminAccountCreateDTO dto) {
        if (this.adminAccountRepository.existed(AdminAccountModel::getCode, dto.getCode())) {
            throw ExceptionUtils.business("编码已存在");
        }

        if (this.adminAccountRepository.existed(AdminAccountModel::getName, dto.getName())) {
            throw ExceptionUtils.business("名称已存在");
        }

        if (this.adminAccountRepository.existed(AdminAccountModel::getUsername, dto.getUsername())) {
            throw ExceptionUtils.business("登录账号已存在");
        }

        AdminAccountModel model = this.adminAccountConvertor.toCreator(dto);

        model.setPassword(HashUtils.getPasswordHash(model.getPassword(), AdminAccountHelper.AUTH_SALT));

        List<AdminAccountRoleModel> roles = this.adminAccountConvertor.toRoleModel(model, dto.getRoleIds());

        return this.dbManager.execute(() -> {
            this.adminAccountRepository.insert(model);

            this.adminAccountRoleRepository.insert(roles);
        });
    }

    @Override
    public Boolean update(AdminAccountUpdateDTO dto) {
        if (this.adminAccountRepository.existed(AdminAccountModel::getName, dto.getName(), dto.getId())) {
            throw ExceptionUtils.business("名称已存在");
        }

        AdminAccountModel model = this.adminAccountConvertor.toUpdater(dto);

        if (EmptyUtils.isNotEmpty(model.getPassword())) {
            model.setPassword(HashUtils.getPasswordHash(model.getPassword(), AdminAccountHelper.AUTH_SALT));
        }

        List<AdminAccountRoleModel> roles = this.adminAccountConvertor.toRoleModel(model, dto.getRoleIds());

        return this.dbManager.execute(() -> {
            this.adminAccountRepository.updateById(model);

            this.adminAccountRoleRepository.remove(AdminAccountRoleModel::getAccountId, model.getId());

            this.adminAccountRoleRepository.insert(roles);
        });
    }

    @Override
    public AdminAccountBaseVO show(Long id) {
        AdminAccountModel model = this.adminAccountRepository.getById(id);

        return this.adminAccountConvertor.toBaseVO(model);
    }

    @Override
    public AdminAccountDetailVO detail(Long id) {
        AdminAccountModel model = this.adminAccountRepository.getById(id);

        Set<Long> roleIds = this.adminAccountRoleRepository.list(
                AdminAccountRoleModel::getAccountId, model.getId(), AdminAccountRoleModel::getRoleId
        );

        return this.adminAccountConvertor.toDetailVO(model, vo -> vo.setRoleIds(new ArrayList<>(roleIds)));
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        List<AdminAccountModel> models = this.adminAccountRepository.listByIds(dto.getIds());

        for (AdminAccountModel model : models) {
            if (BooleanEnums.isTrue(model.getSystemFlag())) {
                throw ExceptionUtils.business(String.format("账号[%s]不允许删除", model.getName()));
            }
        }

        return this.adminAccountRepository.removeByIds(dto.getIds());
    }

}
