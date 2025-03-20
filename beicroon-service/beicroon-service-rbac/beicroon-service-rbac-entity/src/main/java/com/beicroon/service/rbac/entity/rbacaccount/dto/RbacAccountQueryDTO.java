package com.beicroon.service.rbac.entity.rbacaccount.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询账号入参")
@EqualsAndHashCode(callSuper = true)
public class RbacAccountQueryDTO extends QueryDTO {

    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @FieldSearch(name = "`username`")
    @ApiModelProperty(name = "登录账号")
    private String username;

    @FieldSearch(name = "`password`")
    @ApiModelProperty(name = "登录密码")
    private String password;

    @FieldSearch(name = "`phone`")
    @ApiModelProperty(name = "联系电话")
    private String phone;

    @FieldSearch(name = "`email`")
    @ApiModelProperty(name = "邮箱地址")
    private String email;

}
