package com.beicroon.service.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.DisableModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("`admin_account`")
@EqualsAndHashCode(callSuper = true)
public class AccountModel extends DisableModel {

    @TableField(value = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(value = "`account`")
    @ApiModelProperty(name = "账号")
    private String account;

    @TableField(value = "`password`")
    @ApiModelProperty(name = "账号")
    private String password;

    @TableField(value = "`nickname`")
    @ApiModelProperty(name = "姓名")
    private String nickname;

    @TableField(value = "`phone`")
    @ApiModelProperty(name = "手机")
    private String phone;

    @TableField(value = "`email`")
    @ApiModelProperty(name = "邮箱")
    private String email;

}
