package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.dto.ResourceRoleUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleBaseVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRoleDetailVO;
import com.beicroon.starter.web.admin.entity.resource.role.vo.ResourceRolePageVO;
import com.beicroon.starter.web.admin.model.ResourceRoleModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceRoleConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    ResourceRoleModel toEntity(ResourceRoleCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget ResourceRoleModel model, ResourceRoleCreateDTO dto) {
        ConvertorHelper.fillCreator(model);
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    ResourceRoleModel toEntity(ResourceRoleUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget ResourceRoleModel model, ResourceRoleUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    ResourceRoleBaseVO toBaseVO(ResourceRoleModel model);

    ResourceRolePageVO toPageVO(ResourceRoleModel model);

    ResourceRoleDetailVO toDetailVO(ResourceRoleModel model);

    default List<ResourceRoleBaseVO> toBaseVO(List<ResourceRoleModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget ResourceRoleBaseVO vo, ResourceRoleModel model) {

    }

}
