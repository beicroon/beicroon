package com.beicroon.service.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.NeedValue;
import com.beicroon.construct.entity.CreateDTOWithDisable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountAdminCreateDTO extends CreateDTOWithDisable {

    @NeedValue(message = "账号不能为空")
    @ApiModelProperty(name = "账号")
    private String username;

    @NeedValue(message = "昵称不能为空")
    @ApiModelProperty(name = "昵称")
    private String nickname;

    @ApiModelProperty(name = "电话")
    private String phone;

    @ApiModelProperty(name = "邮箱")
    private String email;

}
