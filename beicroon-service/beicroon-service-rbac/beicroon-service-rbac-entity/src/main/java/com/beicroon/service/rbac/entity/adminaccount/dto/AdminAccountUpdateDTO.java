package com.beicroon.service.rbac.entity.adminaccount.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "编辑账号入参")
@EqualsAndHashCode(callSuper = true)
public class AdminAccountUpdateDTO extends IdDTO {

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "登录密码")
    private String password;

    @ApiModelProperty(name = "联系电话")
    private String phone;

    @ApiModelProperty(name = "邮箱地址")
    private String email;

    @ApiModelProperty(name = "角色主键列表")
    private List<Long> roleIds;

}
