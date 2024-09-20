package com.beicroon.starter.web.admin.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.web.admin.entity.account.admin.role.dto.AccountAdminRoleAssignDTO;
import com.beicroon.starter.web.admin.model.AccountAdminRoleModel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountAdminRoleConvertor {

    @Mapping(target = "adminId", source = "adminId")
    @Mapping(target = "roleId", source = "roleId")
    AccountAdminRoleModel toEntity(Long adminId, Long roleId);

    default List<AccountAdminRoleModel> toEntity(Long adminId, List<Long> roleIds) {
        return ListUtils.toList(roleIds, roleId -> this.toEntity(adminId, roleId));
    }

    default List<AccountAdminRoleModel> toEntity(AccountAdminRoleAssignDTO dto) {
        return this.toEntity(dto.getAdminId(), dto.getRoleIds());
    }

    @AfterMapping
    default void fillCreator(@MappingTarget AccountAdminRoleModel model, Long adminId, Long roleId) {
        ConvertorHelper.fillCreator(model);
    }

}
