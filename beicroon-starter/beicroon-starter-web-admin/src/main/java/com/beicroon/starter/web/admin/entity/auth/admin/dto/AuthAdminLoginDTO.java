package com.beicroon.starter.web.admin.entity.auth.admin.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.BaseDTO;
import com.beicroon.construct.validator.annotation.NeedValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "登录认证入参")
@EqualsAndHashCode(callSuper = true)
public class AuthAdminLoginDTO extends BaseDTO {

    @NeedValue(message = "账号[username]不能为空")
    @ApiModelProperty(name = "账号")
    private String username;

    @NeedValue(message = "密码[password]不能为空")
    @ApiModelProperty(name = "密码")
    private String password;

}
