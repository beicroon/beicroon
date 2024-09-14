package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.role.dto.AccountAdminRoleCreateDTO;
import com.beicroon.starter.web.admin.entity.role.vo.AccountAdminRoleBaseVO;
import com.beicroon.starter.web.admin.model.AccountAdminRoleModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAdminRoleConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    AccountAdminRoleModel toEntity(AccountAdminRoleCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget AccountAdminRoleModel model) {
        ConvertorHelper.fillCreator(model);
    }

    AccountAdminRoleBaseVO toBaseVO(AccountAdminRoleModel model);

    default List<AccountAdminRoleBaseVO> toBaseVO(List<AccountAdminRoleModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget AccountAdminRoleBaseVO vo, AccountAdminRoleModel model) {

    }

}
