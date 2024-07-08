package com.beicroon.service.admin.service.impl;

import com.beicroon.service.admin.convertor.AccountConvertor;
import com.beicroon.service.admin.entity.account.vo.AccountBaseVO;
import com.beicroon.service.admin.repository.AccountRepository;
import com.beicroon.service.admin.service.IAccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Resource
    private AccountConvertor accountConvertor;

    @Resource
    private AccountRepository accountRepository;

    @Override
    public AccountBaseVO show(Long id) {
        return this.accountConvertor.toBaseVO(this.accountRepository.getById(id));
    }

}
