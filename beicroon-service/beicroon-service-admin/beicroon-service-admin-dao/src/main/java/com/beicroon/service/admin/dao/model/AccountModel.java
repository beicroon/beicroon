package com.beicroon.service.admin.dao.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.interfaces.IAuthUser;
import com.beicroon.starter.mysql.model.DisableModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("`admin_account`")
@EqualsAndHashCode(callSuper = true)
public class AccountModel extends DisableModel implements IAuthUser {

    @TableField(value = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(value = "`username`")
    @ApiModelProperty(name = "账号")
    private String username;

    @TableField(value = "`password`")
    @ApiModelProperty(name = "密码")
    private String password;

    @TableField(value = "`nickname`")
    @ApiModelProperty(name = "昵称")
    private String nickname;

    @TableField(value = "`phone`")
    @ApiModelProperty(name = "电话")
    private String phone;

    @TableField(value = "`email`")
    @ApiModelProperty(name = "邮箱")
    private String email;

}
