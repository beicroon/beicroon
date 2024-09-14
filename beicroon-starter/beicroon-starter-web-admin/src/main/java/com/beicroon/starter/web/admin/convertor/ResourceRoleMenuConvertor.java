package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.resource.role.menu.dto.ResourceRoleMenuCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.menu.vo.ResourceRoleMenuBaseVO;
import com.beicroon.starter.web.admin.model.ResourceRoleMenuModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceRoleMenuConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    ResourceRoleMenuModel toEntity(ResourceRoleMenuCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget ResourceRoleMenuModel model) {
        ConvertorHelper.fillCreator(model);
    }

    ResourceRoleMenuBaseVO toBaseVO(ResourceRoleMenuModel model);

    default List<ResourceRoleMenuBaseVO> toBaseVO(List<ResourceRoleMenuModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget ResourceRoleMenuBaseVO vo, ResourceRoleMenuModel model) {

    }

}
