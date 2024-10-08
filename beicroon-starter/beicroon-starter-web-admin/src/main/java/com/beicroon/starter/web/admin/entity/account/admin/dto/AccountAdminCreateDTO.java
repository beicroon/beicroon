package com.beicroon.starter.web.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTO;
import com.beicroon.construct.validator.annotation.NeedValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增后台账号入参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminCreateDTO extends CreateDTO {

    @NeedValue(message = "编码[code]不能为空")
    @ApiModelProperty(name = "编码")
    private String code;

    @NeedValue(message = "昵称[name]不能为空")
    @ApiModelProperty(name = "昵称")
    private String name;

    @NeedValue(message = "账号[username]不能为空")
    @ApiModelProperty(name = "账号")
    private String username;

    @NeedValue(message = "密码[password]不能为空")
    @ApiModelProperty(name = "密码")
    private String password;

    @ApiModelProperty(name = "电话")
    private String phone;

    @ApiModelProperty(name = "邮箱")
    private String email;

}
