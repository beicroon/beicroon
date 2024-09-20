package com.beicroon.starter.web.admin.service;

import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleQueryDTO;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.menu.dto.ResourceRoleMenuAssignDTO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleBaseVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleDetailVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRolePageVO;

import java.util.List;

public interface IResourceRoleService {

    ResourceRoleBaseVO show(Long id);

    ResourceRoleDetailVO detail(Long id);

    List<ResourceRoleBaseVO> list(ResourceRoleQueryDTO dto);

    PageInfo<ResourceRolePageVO> page(ResourceRoleQueryDTO dto);

    boolean create(ResourceRoleCreateDTO dto);

    boolean update(ResourceRoleUpdateDTO dto);

    boolean remove(IdsDTO dto);

    List<Long> menuIdList(Long roleId);

    boolean menuAssign(ResourceRoleMenuAssignDTO dto);

}
