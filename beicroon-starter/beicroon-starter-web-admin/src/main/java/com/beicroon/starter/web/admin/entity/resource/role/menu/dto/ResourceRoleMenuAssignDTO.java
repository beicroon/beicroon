package com.beicroon.starter.web.admin.entity.resource.role.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "分配资源角色菜单入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleMenuAssignDTO extends CreateDTO {

    @ApiModelProperty(name = "角色主键")
    private Long roleId;

    @ApiModelProperty(name = "菜单主键列表")
    private List<Long> menuIds;

}
