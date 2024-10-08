package com.beicroon.starter.web.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询后台账号入参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminQueryDTO extends QueryDTO {

    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "昵称")
    private String name;

    @FieldSearch(name = "`username`")
    @ApiModelProperty(name = "账号")
    private String username;

    @FieldSearch(name = "`password`")
    @ApiModelProperty(name = "密码")
    private String password;

    @FieldSearch(name = "`phone`")
    @ApiModelProperty(name = "电话")
    private String phone;

    @FieldSearch(name = "`email`")
    @ApiModelProperty(name = "邮箱")
    private String email;

}
