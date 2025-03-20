package com.beicroon.service.rbac.app.service.impl;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.service.rbac.app.service.IRbacAccountService;
import com.beicroon.service.rbac.dao.convertor.RbacAccountConvertor;
import com.beicroon.service.rbac.dao.helper.RbacAccountHelper;
import com.beicroon.service.rbac.dao.manager.RbacAccountManager;
import com.beicroon.service.rbac.dao.model.RbacAccountModel;
import com.beicroon.service.rbac.dao.model.RbacAccountRoleModel;
import com.beicroon.service.rbac.dao.repository.RbacAccountRepository;
import com.beicroon.service.rbac.dao.repository.RbacAccountRoleRepository;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountCreateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountQueryDTO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountUpdateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountBaseVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountDetailVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountPageVO;
import com.beicroon.starter.database.manager.DBManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RbacAccountService implements IRbacAccountService {

    @Resource
    private DBManager dbManager;

    @Resource
    private RbacAccountManager rbacAccountManager;

    @Resource
    private RbacAccountConvertor rbacAccountConvertor;

    @Resource
    private RbacAccountRepository rbacAccountRepository;

    @Resource
    private RbacAccountRoleRepository rbacAccountRoleRepository;

    @Override
    public List<TabVO> tab(RbacAccountQueryDTO dto) {
        return this.rbacAccountRepository.tab(dto);
    }

    @Override
    public List<RbacAccountBaseVO> list(RbacAccountQueryDTO dto) {
        List<RbacAccountModel> list = this.rbacAccountRepository.list(dto);

        return this.rbacAccountConvertor.toBaseVO(list);
    }

    @Override
    public PageInfo<RbacAccountPageVO> page(RbacAccountQueryDTO dto) {
        PageInfo<RbacAccountModel> page = this.rbacAccountRepository.page(dto);

        return this.rbacAccountConvertor.toPageVO(page);
    }

    @Override
    public Boolean create(RbacAccountCreateDTO dto) {
        if (this.rbacAccountRepository.existed(RbacAccountModel::getCode, dto.getCode())) {
            throw ExceptionUtils.business("编码已存在");
        }

        if (this.rbacAccountRepository.existed(RbacAccountModel::getName, dto.getName())) {
            throw ExceptionUtils.business("名称已存在");
        }

        if (this.rbacAccountRepository.existed(RbacAccountModel::getUsername, dto.getUsername())) {
            throw ExceptionUtils.business("登录账号已存在");
        }

        RbacAccountModel model = this.rbacAccountConvertor.toCreator(dto);

        model.setPassword(HashUtils.getPasswordHash(model.getPassword(), RbacAccountHelper.AUTH_SALT));

        List<RbacAccountRoleModel> roles = this.rbacAccountConvertor.toRoleModel(model, dto.getRoleIds());

        return this.dbManager.execute(() -> {
            this.rbacAccountRepository.insert(model);

            this.rbacAccountRoleRepository.insert(roles);
        });
    }

    @Override
    public Boolean update(RbacAccountUpdateDTO dto) {
        if (this.rbacAccountRepository.existed(RbacAccountModel::getName, dto.getName(), dto.getId())) {
            throw ExceptionUtils.business("名称已存在");
        }

        RbacAccountModel model = this.rbacAccountConvertor.toUpdater(dto);

        if (EmptyUtils.isNotEmpty(model.getPassword())) {
            model.setPassword(HashUtils.getPasswordHash(model.getPassword(), RbacAccountHelper.AUTH_SALT));
        }

        List<RbacAccountRoleModel> roles = this.rbacAccountConvertor.toRoleModel(model, dto.getRoleIds());

        return this.dbManager.execute(() -> {
            this.rbacAccountRepository.updateById(model);

            this.rbacAccountRoleRepository.remove(RbacAccountRoleModel::getAccountId, model.getId());

            this.rbacAccountRoleRepository.insert(roles);
        });
    }

    @Override
    public RbacAccountBaseVO show(Long id) {
        RbacAccountModel model = this.rbacAccountRepository.getById(id);

        return this.rbacAccountConvertor.toBaseVO(model);
    }

    @Override
    public RbacAccountDetailVO detail(Long id) {
        RbacAccountModel model = this.rbacAccountRepository.getById(id);

        Set<Long> roleIds = this.rbacAccountRoleRepository.list(
                RbacAccountRoleModel::getAccountId, model.getId(), RbacAccountRoleModel::getRoleId
        );

        return this.rbacAccountConvertor.toDetailVO(model, vo -> vo.setRoleIds(new ArrayList<>(roleIds)));
    }

    @Override
    public Boolean remove(IdsDTO dto) {
        List<RbacAccountModel> models = this.rbacAccountRepository.listByIds(dto.getIds());

        for (RbacAccountModel model : models) {
            if (BooleanEnums.isTrue(model.getSystemFlag())) {
                throw ExceptionUtils.business(String.format("账号[%s]不允许删除", model.getName()));
            }
        }

        return this.rbacAccountRepository.removeByIds(dto.getIds());
    }

}
