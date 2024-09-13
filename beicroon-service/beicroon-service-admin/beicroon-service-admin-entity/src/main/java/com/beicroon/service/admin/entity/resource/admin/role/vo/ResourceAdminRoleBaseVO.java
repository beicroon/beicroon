package com.beicroon.service.admin.entity.resource.admin.role.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询角色菜单基础出参")
@EqualsAndHashCode(callSuper = true)
public class ResourceAdminRoleBaseVO extends GenericVO {
    
    @ApiModelProperty(name = "后台账号主键")
    private Long adminId;

    @ApiModelProperty(name = "角色主键")
    private Long roleId;

}
