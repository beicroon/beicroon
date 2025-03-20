package com.beicroon.service.rbac.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.rbac.dao.model.RbacResourceModel;
import com.beicroon.service.rbac.entity.rbacauth.vo.RbacAuthResourceVO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceCreateDTO;
import com.beicroon.service.rbac.entity.rbacresource.dto.RbacResourceUpdateDTO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceBaseVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceDetailVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourcePageVO;
import com.beicroon.service.rbac.entity.rbacresource.vo.RbacResourceTreeVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RbacResourceConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    RbacResourceModel toCreator(RbacResourceCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RbacResourceModel model, RbacResourceCreateDTO dto) {
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
    RbacResourceModel toUpdater(RbacResourceUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget RbacResourceModel model, RbacResourceUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    RbacResourceBaseVO toBaseVO(RbacResourceModel model);

    default List<RbacResourceBaseVO> toBaseVO(Collection<RbacResourceModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    RbacResourceTreeVO toTreeVO(RbacResourceModel model);

    RbacResourcePageVO toPageVO(RbacResourceModel model);

    default PageInfo<RbacResourcePageVO> toPageVO(PageInfo<RbacResourceModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<RbacResourcePageVO> toPageVO(PageInfo<RbacResourceModel> page, BiConsumer<RbacResourceModel, RbacResourcePageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            RbacResourcePageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    RbacResourceDetailVO toDetailVO(RbacResourceModel model);

    default RbacResourceDetailVO toDetailVO(RbacResourceModel model, Consumer<RbacResourceDetailVO> consumer) {
        RbacResourceDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget RbacResourceBaseVO vo, RbacResourceModel model) {

    }

    RbacAuthResourceVO toAuthResourceVO(RbacResourceModel model);

}
