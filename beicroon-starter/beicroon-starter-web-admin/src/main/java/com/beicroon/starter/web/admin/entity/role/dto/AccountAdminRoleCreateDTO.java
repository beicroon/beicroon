package com.beicroon.starter.web.admin.entity.role.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增后台账号角色入参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminRoleCreateDTO extends CreateDTO {
    
    @ApiModelProperty(name = "后台账号主键")
    private Long adminId;

    @ApiModelProperty(name = "角色主键")
    private Long roleId;

}
