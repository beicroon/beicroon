package com.beicroon.service.rbac.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.rbac.entity.adminrole.dto.AdminRoleCreateDTO;
import com.beicroon.service.rbac.entity.adminrole.dto.AdminRoleQueryDTO;
import com.beicroon.service.rbac.entity.adminrole.dto.AdminRoleUpdateDTO;
import com.beicroon.service.rbac.entity.adminrole.vo.AdminRoleBaseVO;
import com.beicroon.service.rbac.entity.adminrole.vo.AdminRoleDetailVO;
import com.beicroon.service.rbac.entity.adminrole.vo.AdminRolePageVO;

import java.util.List;

public interface IAdminRoleService {

    List<TabVO> tab(AdminRoleQueryDTO dto);

    List<AdminRoleBaseVO> list(AdminRoleQueryDTO dto);

    PageInfo<AdminRolePageVO> page(AdminRoleQueryDTO dto);

    Boolean create(AdminRoleCreateDTO dto);

    Boolean update(AdminRoleUpdateDTO dto);

    AdminRoleBaseVO show(Long id);

    AdminRoleDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
