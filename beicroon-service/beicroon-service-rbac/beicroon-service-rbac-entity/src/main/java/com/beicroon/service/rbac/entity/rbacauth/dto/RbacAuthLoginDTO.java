package com.beicroon.service.rbac.entity.rbacauth.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "账号登录入参")
@EqualsAndHashCode(callSuper = true)
public class RbacAuthLoginDTO extends GenericDTO {

    @NeedValue(message = "账号不能为空")
    @ApiModelProperty(name = "登录账号")
    private String username;

    @NeedValue(message = "密码不能为空")
    @ApiModelProperty(name = "登录密码")
    private String password;

}
