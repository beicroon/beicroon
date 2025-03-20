package com.beicroon.service.rbac.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.rbac.dao.model.RbacAccountModel;
import com.beicroon.service.rbac.dao.model.RbacAccountRoleModel;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountCreateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.dto.RbacAccountUpdateDTO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountBaseVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountDetailVO;
import com.beicroon.service.rbac.entity.rbacaccount.vo.RbacAccountPageVO;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthLoginVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacAccountConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    RbacAccountModel toCreator(RbacAccountCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RbacAccountModel model, RbacAccountCreateDTO dto) {
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
    RbacAccountModel toUpdater(RbacAccountUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget RbacAccountModel model, RbacAccountUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    RbacAccountBaseVO toBaseVO(RbacAccountModel model);

    default List<RbacAccountBaseVO> toBaseVO(Collection<RbacAccountModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    RbacAccountPageVO toPageVO(RbacAccountModel model);

    default PageInfo<RbacAccountPageVO> toPageVO(PageInfo<RbacAccountModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<RbacAccountPageVO> toPageVO(PageInfo<RbacAccountModel> page, BiConsumer<RbacAccountModel, RbacAccountPageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            RbacAccountPageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    RbacAccountDetailVO toDetailVO(RbacAccountModel model);

    default RbacAccountDetailVO toDetailVO(RbacAccountModel model, Consumer<RbacAccountDetailVO> consumer) {
        RbacAccountDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget RbacAccountBaseVO vo, RbacAccountModel model) {

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
    RbacAccountRoleModel toRoleModel(RbacAccountModel model, Long roleId);

    default List<RbacAccountRoleModel> toRoleModel(RbacAccountModel model, Collection<Long> roleIds) {
        return ListUtils.toList(roleIds, roleId -> this.toRoleModel(model, roleId));
    }

    RbacAuthLoginVO toAuthLoginVO(RbacAccountModel model, String token);

}
