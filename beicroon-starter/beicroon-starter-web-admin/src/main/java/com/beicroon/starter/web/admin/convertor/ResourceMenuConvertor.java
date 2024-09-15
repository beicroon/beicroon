package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.auth.admin.vo.AuthAdminMenuVO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuCreateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.dto.ResourceMenuUpdateDTO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuDetailVO;
import com.beicroon.starter.web.admin.entity.resource.menu.vo.ResourceMenuPageVO;
import com.beicroon.starter.web.admin.model.ResourceMenuModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMenuConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    ResourceMenuModel toEntity(ResourceMenuCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget ResourceMenuModel model, ResourceMenuCreateDTO dto) {
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
    ResourceMenuModel toEntity(ResourceMenuUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget ResourceMenuModel model, ResourceMenuUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    AuthAdminMenuVO toTreeVO(ResourceMenuModel model);

    ResourceMenuBaseVO toBaseVO(ResourceMenuModel model);

    ResourceMenuPageVO toPageVO(ResourceMenuModel model);

    ResourceMenuDetailVO toDetailVO(ResourceMenuModel model);

    default List<ResourceMenuBaseVO> toBaseVO(List<ResourceMenuModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget ResourceMenuBaseVO vo, ResourceMenuModel model) {

    }

}
