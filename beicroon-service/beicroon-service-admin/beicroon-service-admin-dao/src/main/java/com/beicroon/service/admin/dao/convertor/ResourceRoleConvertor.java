package com.beicroon.service.admin.dao.convertor;

import com.beicroon.service.admin.dao.model.ResourceRoleModel;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleCreateDTO;
import com.beicroon.service.admin.entity.resource.role.dto.ResourceRoleUpdateDTO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRoleBaseVO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRoleDetailVO;
import com.beicroon.service.admin.entity.resource.role.vo.ResourceRolePageVO;
import com.beicroon.starter.dao.convertor.GenericConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceRoleConvertor extends GenericConvertor<ResourceRoleModel, ResourceRoleCreateDTO, ResourceRoleUpdateDTO, ResourceRoleBaseVO, ResourceRoleDetailVO, ResourceRolePageVO> {

}
