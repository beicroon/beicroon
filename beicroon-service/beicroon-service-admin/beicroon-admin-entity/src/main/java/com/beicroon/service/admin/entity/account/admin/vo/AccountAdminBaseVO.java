package com.beicroon.service.admin.entity.account.admin.vo;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.DisableVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountAdminBaseVO extends DisableVO {

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "账号")
    private String account;

    @ApiModelProperty(name = "姓名")
    private String nickname;

    @ApiModelProperty(name = "电话")
    private String phone;

    @ApiModelProperty(name = "邮箱")
    private String email;

}