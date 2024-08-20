package com.beicroon.service.admin.entity.auth.admin.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.NeedValue;
import com.beicroon.construct.entity.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "登录认证入参")
@EqualsAndHashCode(callSuper = true)
public class AuthAdminLoginDTO extends BaseDTO {

    @NeedValue(message = "[username]必传")
    @ApiModelProperty(name = "账号")
    private String username;

    @NeedValue(message = "[password]必传")
    @ApiModelProperty(name = "密码")
    private String password;

}
