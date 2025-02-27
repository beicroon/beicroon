package com.beicroon.service.admin.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.admin.entity.adminaccount.dto.AdminAccountCreateDTO;
import com.beicroon.service.admin.entity.adminaccount.dto.AdminAccountQueryDTO;
import com.beicroon.service.admin.entity.adminaccount.dto.AdminAccountUpdateDTO;
import com.beicroon.service.admin.entity.adminaccount.vo.AdminAccountBaseVO;
import com.beicroon.service.admin.entity.adminaccount.vo.AdminAccountDetailVO;
import com.beicroon.service.admin.entity.adminaccount.vo.AdminAccountPageVO;

import java.util.List;

public interface IAdminAccountService {

    List<TabVO> tab(AdminAccountQueryDTO dto);

    List<AdminAccountBaseVO> list(AdminAccountQueryDTO dto);

    PageInfo<AdminAccountPageVO> page(AdminAccountQueryDTO dto);

    Boolean create(AdminAccountCreateDTO dto);

    Boolean update(AdminAccountUpdateDTO dto);

    AdminAccountBaseVO show(Long id);

    AdminAccountDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
