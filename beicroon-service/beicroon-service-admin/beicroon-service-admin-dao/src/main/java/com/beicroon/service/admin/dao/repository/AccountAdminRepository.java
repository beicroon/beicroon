package com.beicroon.service.admin.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.service.admin.dao.mapper.AccountAdminMapper;
import com.beicroon.service.admin.dao.model.AccountAdminModel;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountAdminRepository extends GenericRepository<AccountAdminQueryDTO, AccountAdminMapper, AccountAdminModel> implements IGenericRepository<AccountAdminQueryDTO, AccountAdminModel> {

    @Override
    public void fillQueryWrapper(AccountAdminQueryDTO dto, QueryWrapper<AccountAdminModel> wrapper) {

    }

}
