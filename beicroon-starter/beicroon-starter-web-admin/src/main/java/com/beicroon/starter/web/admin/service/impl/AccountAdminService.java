package com.beicroon.starter.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.HashUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.jwt.utils.JwtUtils;
import com.beicroon.starter.mysql.utils.PageUtils;
import com.beicroon.starter.web.admin.convertor.AccountAdminConvertor;
import com.beicroon.starter.web.admin.convertor.AccountAdminRoleConvertor;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminCreateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.role.dto.AccountAdminRoleAssignDTO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.starter.web.admin.helper.AccountAdminHelper;
import com.beicroon.starter.web.admin.model.AccountAdminModel;
import com.beicroon.starter.web.admin.model.AccountAdminRoleModel;
import com.beicroon.starter.web.admin.repository.AccountAdminRepository;
import com.beicroon.starter.web.admin.repository.AccountAdminRoleRepository;
import com.beicroon.starter.web.admin.service.IAccountAdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountAdminService implements IAccountAdminService {

    @Resource
    private AccountAdminConvertor accountAdminConvertor;

    @Resource
    private AccountAdminRoleConvertor accountAdminRoleConvertor;

    @Resource
    private AccountAdminRepository accountAdminRepository;

    @Resource
    private AccountAdminRoleRepository accountAdminRoleRepository;

    @Override
    public AccountAdminBaseVO show(Long id) {
        AccountAdminModel accountAdmin = this.accountAdminRepository.getById(id);

        return this.accountAdminConvertor.toBaseVO(accountAdmin);
    }

    @Override
    public AccountAdminDetailVO detail(Long id) {
        AccountAdminModel accountAdmin = this.accountAdminRepository.getById(id);

        return this.accountAdminConvertor.toDetailVO(accountAdmin);
    }

    @Override
    public List<AccountAdminBaseVO> list(AccountAdminQueryDTO dto) {
        QueryWrapper<AccountAdminModel> wrapper = this.accountAdminRepository.newPageWrapper(dto);

        AccountAdminModel admin = this.accountAdminRepository.getById(AuthUtils.getUserId());

        if (AccountAdminHelper.isNotRoot(admin)) {
            wrapper.lambda().eq(AccountAdminModel::getRootFlag, BooleanEnums.FALSE.getCode());
        }

        List<AccountAdminModel> list = this.accountAdminRepository.list(wrapper);

        return ListUtils.toList(list, this.accountAdminConvertor::toBaseVO);
    }

    @Override
    public PageInfo<AccountAdminPageVO> page(AccountAdminQueryDTO dto) {
        QueryWrapper<AccountAdminModel> wrapper = this.accountAdminRepository.newPageWrapper(dto);

        AccountAdminModel admin = this.accountAdminRepository.getById(AuthUtils.getUserId());

        if (AccountAdminHelper.isNotRoot(admin)) {
            wrapper.lambda().eq(AccountAdminModel::getRootFlag, BooleanEnums.FALSE.getCode());
        }

        Page<AccountAdminModel> page = this.accountAdminRepository.page(dto, wrapper);

        return PageUtils.result(page, this.accountAdminConvertor::toPageVO);
    }

    @Override
    public boolean create(AccountAdminCreateDTO dto) {
        if (this.accountAdminRepository.existed(AccountAdminModel::getCode, dto.getCode())) {
            throw ExceptionUtils.business("账号编码已被占用");
        }

        if (this.accountAdminRepository.existed(AccountAdminModel::getName, dto.getCode())) {
            throw ExceptionUtils.business("账号编码已被占用");
        }

        AccountAdminModel creator = this.accountAdminConvertor.toEntity(dto);

        creator.setPassword(HashUtils.getPasswordHash(creator.getPassword(), JwtUtils.getSaltString()));

        return this.accountAdminRepository.save(creator);
    }

    @Override
    public boolean update(AccountAdminUpdateDTO dto) {
        AccountAdminModel updater = this.accountAdminConvertor.toEntity(dto);

        if (EmptyUtils.isNotEmpty(updater.getPassword())) {
            updater.setPassword(HashUtils.getPasswordHash(updater.getPassword(), JwtUtils.getSaltString()));
        }

        return this.accountAdminRepository.updateById(updater);
    }

    @Override
    public boolean remove(IdsDTO dto) {
        return this.accountAdminRepository.removeByIds(dto.getIds());
    }

    @Override
    public List<Long> roleIdList(Long adminId) {
        Set<Long> roleIds = this.accountAdminRoleRepository.list(
                AccountAdminRoleModel::getAdminId, adminId, AccountAdminRoleModel::getRoleId
        );

        return new ArrayList<>(roleIds);
    }

    @Override
    public boolean roleAssign(AccountAdminRoleAssignDTO dto) {
        this.accountAdminRoleRepository.remove(AccountAdminRoleModel::getAdminId, dto.getAdminId());

        return this.accountAdminRoleRepository.saveBatch(this.accountAdminRoleConvertor.toEntity(dto));
    }

}
