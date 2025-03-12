package com.beicroon.service.rbac.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.rbac.dao.model.AdminAccountModel;
import com.beicroon.service.rbac.dao.model.AdminAccountRoleModel;
import com.beicroon.service.rbac.entity.adminaccount.dto.AdminAccountCreateDTO;
import com.beicroon.service.rbac.entity.adminaccount.dto.AdminAccountUpdateDTO;
import com.beicroon.service.rbac.entity.adminaccount.vo.AdminAccountBaseVO;
import com.beicroon.service.rbac.entity.adminaccount.vo.AdminAccountDetailVO;
import com.beicroon.service.rbac.entity.adminaccount.vo.AdminAccountPageVO;
import com.beicroon.service.rbac.entity.auth.vo.AdminAuthLoginVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminAccountConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    AdminAccountModel toCreator(AdminAccountCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget AdminAccountModel model, AdminAccountCreateDTO dto) {
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
    AdminAccountModel toUpdater(AdminAccountUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget AdminAccountModel model, AdminAccountUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    AdminAccountBaseVO toBaseVO(AdminAccountModel model);

    default List<AdminAccountBaseVO> toBaseVO(Collection<AdminAccountModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    AdminAccountPageVO toPageVO(AdminAccountModel model);

    default PageInfo<AdminAccountPageVO> toPageVO(PageInfo<AdminAccountModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<AdminAccountPageVO> toPageVO(PageInfo<AdminAccountModel> page, BiConsumer<AdminAccountModel, AdminAccountPageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            AdminAccountPageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    AdminAccountDetailVO toDetailVO(AdminAccountModel model);

    default AdminAccountDetailVO toDetailVO(AdminAccountModel model, Consumer<AdminAccountDetailVO> consumer) {
        AdminAccountDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget AdminAccountBaseVO vo, AdminAccountModel model) {

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
    @Mapping(target = "accountId", source = "model.id")
    @Mapping(target = "roleId", source = "roleId")
    AdminAccountRoleModel toRoleModel(AdminAccountModel model, Long roleId);

    default List<AdminAccountRoleModel> toRoleModel(AdminAccountModel model, Collection<Long> roleIds) {
        return ListUtils.toList(roleIds, roleId -> this.toRoleModel(model, roleId));
    }

    AdminAuthLoginVO toAuthLoginVO(AdminAccountModel model, String token);

}
