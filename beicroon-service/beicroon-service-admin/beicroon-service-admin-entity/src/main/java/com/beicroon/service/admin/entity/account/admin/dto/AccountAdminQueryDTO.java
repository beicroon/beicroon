package com.beicroon.service.admin.entity.account.admin.dto;

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
    
    @FieldSearch(value = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(value = "`username`")
    @ApiModelProperty(name = "账号")
    private String username;

    @FieldSearch(value = "`password`")
    @ApiModelProperty(name = "密码")
    private String password;

    @FieldSearch(value = "`nickname`")
    @ApiModelProperty(name = "昵称")
    private String nickname;

    @FieldSearch(value = "`phone`")
    @ApiModelProperty(name = "电话")
    private String phone;

    @FieldSearch(value = "`email`")
    @ApiModelProperty(name = "邮箱")
    private String email;

}
