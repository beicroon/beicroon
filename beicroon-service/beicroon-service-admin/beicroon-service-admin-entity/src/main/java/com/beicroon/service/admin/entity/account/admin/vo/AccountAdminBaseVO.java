package com.beicroon.service.admin.entity.account.admin.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询后台账号基础出参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminBaseVO extends GenericVO {
    
    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "账号")
    private String username;

    @ApiModelProperty(name = "密码")
    private String password;

    @ApiModelProperty(name = "昵称")
    private String nickname;

    @ApiModelProperty(name = "电话")
    private String phone;

    @ApiModelProperty(name = "邮箱")
    private String email;

}
