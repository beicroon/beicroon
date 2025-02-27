package com.beicroon.service.admin.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`admin_role`")
public class AdminRoleModel extends GenericModel {

    @TableField(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @TableField(name = "`system_flag`")
    @ApiModelProperty(name = "是否系统创建")
    private String systemFlag;

}
