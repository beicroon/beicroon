package com.beicroon.service.admin.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.admin.dao.model.AdminResourceModel;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceCreateDTO;
import com.beicroon.service.admin.entity.adminresource.dto.AdminResourceUpdateDTO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceBaseVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceDetailVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourcePageVO;
import com.beicroon.service.admin.entity.adminresource.vo.AdminResourceTreeVO;
import com.beicroon.service.admin.entity.auth.vo.AdminAuthResourceVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminResourceConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    AdminResourceModel toCreator(AdminResourceCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget AdminResourceModel model, AdminResourceCreateDTO dto) {
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
    AdminResourceModel toUpdater(AdminResourceUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget AdminResourceModel model, AdminResourceUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    AdminResourceBaseVO toBaseVO(AdminResourceModel model);

    default List<AdminResourceBaseVO> toBaseVO(Collection<AdminResourceModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    AdminResourceTreeVO toTreeVO(AdminResourceModel model);

    AdminResourcePageVO toPageVO(AdminResourceModel model);

    default PageInfo<AdminResourcePageVO> toPageVO(PageInfo<AdminResourceModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<AdminResourcePageVO> toPageVO(PageInfo<AdminResourceModel> page, BiConsumer<AdminResourceModel, AdminResourcePageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            AdminResourcePageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    AdminResourceDetailVO toDetailVO(AdminResourceModel model);

    default AdminResourceDetailVO toDetailVO(AdminResourceModel model, Consumer<AdminResourceDetailVO> consumer) {
        AdminResourceDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget AdminResourceBaseVO vo, AdminResourceModel model) {

    }

    AdminAuthResourceVO toAuthResourceVO(AdminResourceModel model);

}
