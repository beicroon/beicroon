package com.beicroon.service.rbac.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleCreateDTO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleQueryDTO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleUpdateDTO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleBaseVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleDetailVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRolePageVO;

import java.util.List;

public interface IRbacRoleService {

    List<TabVO> tab(RbacRoleQueryDTO dto);

    List<RbacRoleBaseVO> list(RbacRoleQueryDTO dto);

    PageInfo<RbacRolePageVO> page(RbacRoleQueryDTO dto);

    Boolean create(RbacRoleCreateDTO dto);

    Boolean update(RbacRoleUpdateDTO dto);

    RbacRoleBaseVO show(Long id);

    RbacRoleDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
