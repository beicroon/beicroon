package com.beicroon.service.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTOWithDisable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountAdminUpdateDTO extends UpdateDTOWithDisable {

    @ApiModelProperty(name = "姓名")
    private String nickname;

    @ApiModelProperty(name = "手机")
    private String phone;

    @ApiModelProperty(name = "邮箱")
    private String email;

}
