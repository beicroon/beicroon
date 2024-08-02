package com.beicroon.service.admin.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.service.admin.mapper.AccountMapper;
import com.beicroon.service.admin.model.AccountModel;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountAdminRepository extends GenericRepository<AccountAdminQueryDTO, AccountMapper, AccountModel> implements IGenericRepository<AccountAdminQueryDTO, AccountModel> {

    @Override
    public void fillQueryWrapper(AccountAdminQueryDTO dto, QueryWrapper<AccountModel> wrapper) {

    }

}
