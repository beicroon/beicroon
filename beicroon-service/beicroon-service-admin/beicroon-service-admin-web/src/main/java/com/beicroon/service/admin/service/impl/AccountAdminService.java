package com.beicroon.service.admin.service.impl;

import com.beicroon.common.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.convertor.AccountAdminConvertor;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.service.admin.repository.AccountAdminRepository;
import com.beicroon.service.admin.service.IAccountAdminService;
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
        return this.accountAdminConvertor.toBaseVO(this.accountAdminRepository.getById(id));
    }

    @Override
    public AccountAdminDetailVO detail(Long id) {
        return this.accountAdminConvertor.toDetailVO(this.accountAdminRepository.getById(id));
    }

    @Override
    public List<AccountAdminBaseVO> list(AccountAdminQueryDTO dto) {
        return ListUtils.toList(this.accountAdminRepository.list(dto), this.accountAdminConvertor::toBaseVO);
    }

    @Override
    public PageInfo<AccountAdminPageVO> page(AccountAdminQueryDTO dto) {
        return PageUtils.result(this.accountAdminRepository.page(dto), this.accountAdminConvertor::toPageVO);
    }

}
