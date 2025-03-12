package com.beicroon.service.rbac.entity.auth.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "账号登录入参")
@EqualsAndHashCode(callSuper = true)
public class AdminAuthLoginVO extends GenericDTO {

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "登录账号")
    private String username;

    @ApiModelProperty(name = "联系电话")
    private String phone;

    @ApiModelProperty(name = "邮箱地址")
    private String email;

    @ApiModelProperty(name = "认证信息")
    private String token;

}
