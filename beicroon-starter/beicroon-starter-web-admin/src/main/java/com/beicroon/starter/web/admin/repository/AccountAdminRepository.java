package com.beicroon.starter.web.admin.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.starter.mysql.repository.IGenericRepository;
import com.beicroon.starter.mysql.repository.impl.GenericRepository;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.starter.web.admin.mapper.AccountAdminMapper;
import com.beicroon.starter.web.admin.model.AccountAdminModel;
import org.springframework.stereotype.Component;

@Component
public class AccountAdminRepository extends GenericRepository<AccountAdminQueryDTO, AccountAdminMapper, AccountAdminModel> implements IGenericRepository<AccountAdminQueryDTO, AccountAdminModel> {

    @Override
    public void fillQueryWrapper(AccountAdminQueryDTO dto, QueryWrapper<AccountAdminModel> wrapper) {
        LambdaQueryWrapper<AccountAdminModel> query = wrapper.lambda();
    }

}
