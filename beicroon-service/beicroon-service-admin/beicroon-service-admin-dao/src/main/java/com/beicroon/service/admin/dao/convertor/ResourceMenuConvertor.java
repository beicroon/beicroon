package com.beicroon.service.admin.dao.convertor;

import com.beicroon.service.admin.dao.model.ResourceMenuModel;
import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
import com.beicroon.service.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuPageVO;
import com.beicroon.starter.dao.convertor.GenericConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMenuConvertor extends GenericConvertor<ResourceMenuModel, ResourceMenuCreateDTO, ResourceMenuUpdateDTO, ResourceMenuBaseVO, ResourceMenuDetailVO, ResourceMenuPageVO> {

}
