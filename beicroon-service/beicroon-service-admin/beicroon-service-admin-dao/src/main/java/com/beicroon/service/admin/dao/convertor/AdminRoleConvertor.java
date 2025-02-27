package com.beicroon.service.admin.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.dao.model.AdminRoleModel;
import com.beicroon.service.admin.dao.model.AdminRoleResourceModel;
import com.beicroon.service.admin.entity.adminrole.dto.AdminRoleCreateDTO;
import com.beicroon.service.admin.entity.adminrole.dto.AdminRoleUpdateDTO;
import com.beicroon.service.admin.entity.adminrole.vo.AdminRoleBaseVO;
import com.beicroon.service.admin.entity.adminrole.vo.AdminRoleDetailVO;
import com.beicroon.service.admin.entity.adminrole.vo.AdminRolePageVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminRoleConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    AdminRoleModel toCreator(AdminRoleCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget AdminRoleModel model, AdminRoleCreateDTO dto) {
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
    AdminRoleModel toUpdater(AdminRoleUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget AdminRoleModel model, AdminRoleUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    AdminRoleBaseVO toBaseVO(AdminRoleModel model);

    default List<AdminRoleBaseVO> toBaseVO(Collection<AdminRoleModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    AdminRolePageVO toPageVO(AdminRoleModel model);

    default PageInfo<AdminRolePageVO> toPageVO(PageInfo<AdminRoleModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<AdminRolePageVO> toPageVO(PageInfo<AdminRoleModel> page, BiConsumer<AdminRoleModel, AdminRolePageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            AdminRolePageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    AdminRoleDetailVO toDetailVO(AdminRoleModel model);

    default AdminRoleDetailVO toDetailVO(AdminRoleModel model, Consumer<AdminRoleDetailVO> consumer) {
        AdminRoleDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget AdminRoleBaseVO vo, AdminRoleModel model) {

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
    AdminRoleResourceModel toResourceModel(AdminRoleModel model, Long resourceId);

    default List<AdminRoleResourceModel> toResourceModel(AdminRoleModel model, List<Long> resourceIds) {
        return ListUtils.toList(resourceIds, resourceId -> this.toResourceModel(model, resourceId));
    }

    @AfterMapping
    default void fillCreator(@MappingTarget AdminRoleResourceModel model) {
        ConvertorHelper.fillCreator(model);
    }

}
