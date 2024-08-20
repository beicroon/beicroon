package com.beicroon.starter.web.admin.convertor;

import com.beicroon.starter.dao.convertor.GenericConvertor;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminMenuVO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuPageVO;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMenuConvertor extends GenericConvertor<ResourceMenuModel, ResourceMenuCreateDTO, ResourceMenuUpdateDTO, ResourceMenuBaseVO, ResourceMenuDetailVO, ResourceMenuPageVO> {

    AuthAdminMenuVO toTreeVO(ResourceMenuModel model);

}
