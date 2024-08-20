package com.beicroon.starter.web.admin.service;

import com.beicroon.construct.entity.IdsDTO;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuQueryDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuPageVO;

import java.util.List;

public interface IResourceMenuService {

    ResourceMenuBaseVO show(Long id);

    ResourceMenuDetailVO detail(Long id);

    List<ResourceMenuBaseVO> list(ResourceMenuQueryDTO dto);

    PageInfo<ResourceMenuPageVO> page(ResourceMenuQueryDTO dto);

    boolean create(ResourceMenuCreateDTO dto);

    boolean update(ResourceMenuUpdateDTO dto);

    boolean remove(IdsDTO dto);

}
