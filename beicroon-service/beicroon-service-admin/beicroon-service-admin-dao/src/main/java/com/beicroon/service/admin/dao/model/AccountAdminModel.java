package com.beicroon.service.admin.dao.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "后台账号")
@TableName("`account_admin`")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminModel extends GenericModel {
    
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
