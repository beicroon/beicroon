package com.beicroon.starter.web.admin.entity.resource.role.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增角色菜单入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleMenuCreateDTO extends CreateDTO {
    
    @ApiModelProperty(name = "角色主键")
    private Long roleId;

    @ApiModelProperty(name = "菜单主键")
    private Long menuId;

}
