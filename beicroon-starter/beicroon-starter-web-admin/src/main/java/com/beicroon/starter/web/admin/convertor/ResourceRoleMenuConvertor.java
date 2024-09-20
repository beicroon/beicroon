package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.resource.role.menu.ResourceRoleMenuAssignDTO;
import com.beicroon.starter.web.admin.model.ResourceRoleMenuModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceRoleMenuConvertor {

    @Mapping(target = "roleId", source = "roleId")
    @Mapping(target = "menuId", source = "menuId")
    ResourceRoleMenuModel toEntity(Long roleId, Long menuId);

    default List<ResourceRoleMenuModel> toEntity(Long roleId, List<Long> menuIds) {
        return ListUtils.toList(menuIds, menuId -> this.toEntity(roleId, menuId));
    }

    default List<ResourceRoleMenuModel> toEntity(ResourceRoleMenuAssignDTO dto) {
        return this.toEntity(dto.getRoleId(), dto.getMenuIds());
    }

    @AfterMapping
    default void fillCreator(@MappingTarget ResourceRoleMenuModel model, Long roleId, Long menuId) {
        ConvertorHelper.fillCreator(model);
    }

}
