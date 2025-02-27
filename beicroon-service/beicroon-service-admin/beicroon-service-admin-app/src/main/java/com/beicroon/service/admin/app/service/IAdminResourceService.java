package com.beicroon.service.admin.app.service;

import com.beicroon.construct.base.entity.IdsDTO;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceCreateDTO;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceQueryDTO;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceUpdateDTO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceBaseVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceDetailVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourcePageVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceTreeVO;

import java.util.List;

public interface IAdminResourceService {

    List<TabVO> tab(AdminResourceQueryDTO dto);

    List<AdminResourceBaseVO> list(AdminResourceQueryDTO dto);

    List<AdminResourceTreeVO> tree(AdminResourceQueryDTO dto);

    PageInfo<AdminResourcePageVO> page(AdminResourceQueryDTO dto);

    Boolean create(AdminResourceCreateDTO dto);

    Boolean update(AdminResourceUpdateDTO dto);

    AdminResourceBaseVO show(Long id);

    AdminResourceDetailVO detail(Long id);

    Boolean remove(IdsDTO dto);

}
