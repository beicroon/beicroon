package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.account.admin.role.dto.ResourceAdminRoleCreateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.role.vo.ResourceAdminRoleBaseVO;
import com.beicroon.starter.web.admin.model.ResourceAdminRoleModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceAdminRoleConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    ResourceAdminRoleModel toEntity(ResourceAdminRoleCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget ResourceAdminRoleModel model) {
        ConvertorHelper.fillCreator(model);
    }

    ResourceAdminRoleBaseVO toBaseVO(ResourceAdminRoleModel model);

    default List<ResourceAdminRoleBaseVO> toBaseVO(List<ResourceAdminRoleModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget ResourceAdminRoleBaseVO vo, ResourceAdminRoleModel model) {

    }

}
