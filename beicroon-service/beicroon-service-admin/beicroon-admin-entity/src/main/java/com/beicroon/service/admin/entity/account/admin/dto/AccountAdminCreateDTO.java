package com.beicroon.service.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTOWithDisable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountAdminCreateDTO extends CreateDTOWithDisable {

    @ApiModelProperty(name = "账号")
    private String account;

    @ApiModelProperty(name = "姓名")
    private String nickname;

    @ApiModelProperty(name = "手机")
    private String phone;

    @ApiModelProperty(name = "邮箱")
    private String email;

}
