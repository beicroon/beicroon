package com.beicroon.service.rbac.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceCreateDTO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceQueryDTO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceUpdateDTO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceBaseVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceDetailVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourcePageVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceTreeVO;

import java.util.List;

public interface IRbacResourceService {

    List<TabVO> tab(RbacResourceQueryDTO dto);

    List<RbacResourceBaseVO> list(RbacResourceQueryDTO dto);

    List<RbacResourceTreeVO> tree(RbacResourceQueryDTO dto);

    PageInfo<RbacResourcePageVO> page(RbacResourceQueryDTO dto);

    Boolean create(RbacResourceCreateDTO dto);

    Boolean update(RbacResourceUpdateDTO dto);

    RbacResourceBaseVO show(Long id);

    RbacResourceDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
