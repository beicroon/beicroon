package com.beicroon.starter.web.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "角色菜单")
@TableName("`admin_resource_role_menu_relation`")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleMenuModel extends GenericModel {
    
    @TableField(value = "`role_id`")
    @ApiModelProperty(name = "角色主键")
    private Long roleId;

    @TableField(value = "`menu_id`")
    @ApiModelProperty(name = "菜单主键")
    private Long menuId;

}
