package com.beicroon.service.rbac.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`admin_account_role_relation`")
public class AdminAccountRoleModel extends GenericModel {

    @TableField(name = "`account_id`")
    @ApiModelProperty(name = "账号主键")
    private Long accountId;

    @TableField(name = "`role_id`")
    @ApiModelProperty(name = "角色主键")
    private Long roleId;

}
