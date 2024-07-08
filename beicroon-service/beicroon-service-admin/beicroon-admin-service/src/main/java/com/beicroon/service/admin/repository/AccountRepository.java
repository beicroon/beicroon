package com.beicroon.service.admin.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.service.admin.entity.account.dto.AccountQueryDTO;
import com.beicroon.service.admin.mapper.AccountMapper;
import com.beicroon.service.admin.model.AccountModel;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountRepository extends GenericRepository<AccountQueryDTO, AccountMapper, AccountModel> implements IGenericRepository<AccountQueryDTO, AccountModel> {

    @Override
    public void fillQueryWrapper(AccountQueryDTO dto, QueryWrapper<AccountModel> wrapper) {

    }

}
