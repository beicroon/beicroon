package com.beicroon.service.rbac.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountCreateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountQueryDTO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountUpdateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountBaseVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountDetailVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountPageVO;

import java.util.List;

public interface IRbacAccountService {

    List<TabVO> tab(RbacAccountQueryDTO dto);

    List<RbacAccountBaseVO> list(RbacAccountQueryDTO dto);

    PageInfo<RbacAccountPageVO> page(RbacAccountQueryDTO dto);

    Boolean create(RbacAccountCreateDTO dto);

    Boolean update(RbacAccountUpdateDTO dto);

    RbacAccountBaseVO show(Long id);

    RbacAccountDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
