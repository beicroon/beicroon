package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminCreateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.dto.AccountAdminUpdateDTO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminBaseVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminDetailVO;
import com.beicroon.starter.web.admin.entity.account.admin.vo.AccountAdminPageVO;
import com.beicroon.starter.web.admin.model.AccountAdminModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAdminConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    AccountAdminModel toEntity(AccountAdminCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget AccountAdminModel model) {
        ConvertorHelper.fillCreator(model);
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
    AccountAdminModel toEntity(AccountAdminUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget AccountAdminModel model, AccountAdminUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    AccountAdminBaseVO toBaseVO(AccountAdminModel model);

    AccountAdminPageVO toPageVO(AccountAdminModel model);

    AccountAdminDetailVO toDetailVO(AccountAdminModel model);

    default List<AccountAdminBaseVO> toBaseVO(List<AccountAdminModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget AccountAdminBaseVO vo, AccountAdminModel model) {

    }

}
