package com.beicroon.service.admin.service;

import com.beicroon.common.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.service.admin.entity.account.admin.dto.AccountAdminQueryDTO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.service.admin.entity.account.admin.vo.AccountAdminPageVO;

import java.util.List;

public interface IAccountAdminService {

    AccountAdminBaseVO show(Long id);

    AccountAdminDetailVO detail(Long id);

    List<AccountAdminBaseVO> list(AccountAdminQueryDTO dto);

    PageInfo<AccountAdminPageVO> page(AccountAdminQueryDTO dto);

}
