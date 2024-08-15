package com.beicroon.service.admin.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.dao.convertor.AccountAdminConvertor;
import com.beicroon.service.admin.dao.model.AccountAdminModel;
import com.beicroon.service.admin.dao.repository.AccountAdminRepository;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminCreateDTO;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.service.admin.web.service.IAccountAdminService;
import com.beicroon.starter.mysql.utils.PageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountAdminService implements IAccountAdminService {

    @Resource
    private AccountAdminConvertor accountAdminConvertor;

    @Resource
    private AccountAdminRepository accountAdminRepository;

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
        List<AccountAdminModel> list = this.accountAdminRepository.list(dto);
        
        return ListUtils.toList(list, this.accountAdminConvertor::toBaseVO);
    }

    @Override
    public PageInfo<AccountAdminPageVO> page(AccountAdminQueryDTO dto) {
        Page<AccountAdminModel> page = this.accountAdminRepository.page(dto);
        
        return PageUtils.result(page, this.accountAdminConvertor::toPageVO);
    }

    @Override
    public boolean create(AccountAdminCreateDTO dto) {
        AccountAdminModel creator = this.accountAdminConvertor.toEntity(dto);
        
        return this.accountAdminRepository.save(creator);
    }

    @Override
    public boolean update(AccountAdminUpdateDTO dto) {
        AccountAdminModel updater = this.accountAdminConvertor.toEntity(dto);
        
        return this.accountAdminRepository.updateById(updater);
    }

    @Override
    public boolean remove(IdsDTO dto) {
        return this.accountAdminRepository.removeByIds(dto.getIds());
    }

}
