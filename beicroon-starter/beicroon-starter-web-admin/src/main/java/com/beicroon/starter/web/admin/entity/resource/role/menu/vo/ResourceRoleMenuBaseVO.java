package com.beicroon.starter.web.admin.entity.resource.role.menu.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询角色菜单基础出参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleMenuBaseVO extends GenericVO {
    
    @ApiModelProperty(name = "角色主键")
    private Long roleId;

    @ApiModelProperty(name = "菜单主键")
    private Long menuId;

}
