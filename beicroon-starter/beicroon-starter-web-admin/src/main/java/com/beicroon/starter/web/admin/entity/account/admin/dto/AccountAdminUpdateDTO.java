package com.beicroon.starter.web.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTO;
import com.beicroon.construct.validator.annotation.NeedValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "修改后台账号入参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminUpdateDTO extends UpdateDTO {

    @NeedValue(message = "昵称[name]不能为空")
    @ApiModelProperty(name = "昵称")
    private String name;

    @ApiModelProperty(name = "密码")
    private String password;

    @ApiModelProperty(name = "电话")
    private String phone;

    @ApiModelProperty(name = "邮箱")
    private String email;

}
