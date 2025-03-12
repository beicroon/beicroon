package com.beicroon.service.rbac.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`admin_role_resource_relation`")
public class AdminRoleResourceModel extends GenericModel {

    @TableField(name = "`role_id`")
    @ApiModelProperty(name = "角色主键")
    private Long roleId;

    @TableField(name = "`resource_id`")
    @ApiModelProperty(name = "资源主键")
    private Long resourceId;

}
