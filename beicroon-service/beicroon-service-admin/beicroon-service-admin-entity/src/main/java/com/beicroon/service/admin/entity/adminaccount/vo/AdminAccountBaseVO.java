package com.beicroon.service.admin.entity.adminaccount.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询账号基础出参")
@EqualsAndHashCode(callSuper = true)
public class AdminAccountBaseVO extends GenericVO {

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

}
