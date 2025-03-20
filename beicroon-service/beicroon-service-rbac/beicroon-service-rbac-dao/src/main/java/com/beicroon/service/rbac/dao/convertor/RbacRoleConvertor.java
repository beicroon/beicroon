package com.beicroon.service.rbac.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.rbac.dao.model.RbacRoleModel;
import com.beicroon.service.rbac.dao.model.RbacRoleResourceModel;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleCreateDTO;
import com.beicroon.service.rbac.entity.rbacrole.dto.RbacRoleUpdateDTO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleBaseVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRoleDetailVO;
import com.beicroon.service.rbac.entity.rbacrole.vo.RbacRolePageVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacRoleConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    RbacRoleModel toCreator(RbacRoleCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RbacRoleModel model, RbacRoleCreateDTO dto) {
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
    RbacRoleModel toUpdater(RbacRoleUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget RbacRoleModel model, RbacRoleUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    RbacRoleBaseVO toBaseVO(RbacRoleModel model);

    default List<RbacRoleBaseVO> toBaseVO(Collection<RbacRoleModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    RbacRolePageVO toPageVO(RbacRoleModel model);

    default PageInfo<RbacRolePageVO> toPageVO(PageInfo<RbacRoleModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<RbacRolePageVO> toPageVO(PageInfo<RbacRoleModel> page, BiConsumer<RbacRoleModel, RbacRolePageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            RbacRolePageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    RbacRoleDetailVO toDetailVO(RbacRoleModel model);

    default RbacRoleDetailVO toDetailVO(RbacRoleModel model, Consumer<RbacRoleDetailVO> consumer) {
        RbacRoleDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget RbacRoleBaseVO vo, RbacRoleModel model) {

    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    @Mapping(target = "roleId", source = "model.id")
    @Mapping(target = "resourceId", source = "resourceId")
    RbacRoleResourceModel toResourceModel(RbacRoleModel model, Long resourceId);

    default List<RbacRoleResourceModel> toResourceModel(RbacRoleModel model, List<Long> resourceIds) {
        return ListUtils.toList(resourceIds, resourceId -> this.toResourceModel(model, resourceId));
    }

    @AfterMapping
    default void fillCreator(@MappingTarget RbacRoleResourceModel model) {
        ConvertorHelper.fillCreator(model);
    }

}
