package com.beicroon.service.admin.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.base.interfaces.IAuthUser;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`admin_account`")
public class AdminAccountModel extends GenericModel implements IAuthUser {

    @TableField(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @TableField(name = "`username`")
    @ApiModelProperty(name = "登录账号")
    private String username;

    @TableField(name = "`password`")
    @ApiModelProperty(name = "登录密码")
    private String password;

    @TableField(name = "`phone`")
    @ApiModelProperty(name = "联系电话")
    private String phone;

    @TableField(name = "`email`")
    @ApiModelProperty(name = "邮箱地址")
    private String email;

    @TableField(name = "`system_flag`")
    @ApiModelProperty(name = "是否系统创建")
    private String systemFlag;

}
