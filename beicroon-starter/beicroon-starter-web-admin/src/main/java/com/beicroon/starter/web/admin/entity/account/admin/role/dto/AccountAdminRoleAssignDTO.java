package com.beicroon.starter.web.admin.entity.account.admin.role.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "分配后台账号角色入参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminRoleAssignDTO extends CreateDTO {

    @ApiModelProperty(name = "账号主键")
    private Long adminId;

    @ApiModelProperty(name = "角色主键列表")
    private List<Long> roleIds;

}
