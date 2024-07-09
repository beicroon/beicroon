package com.beicroon.service.admin.entity.account.dto;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.entity.QueryDTOWithDisable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountQueryDTO extends QueryDTOWithDisable {

    @FieldSearch(value = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(value = "`account`")
    @ApiModelProperty(name = "账号")
    private String account;

    @FieldSearch(value = "`nickname`")
    @ApiModelProperty(name = "姓名")
    private String nickname;

    @FieldSearch(value = "`phone`")
    @ApiModelProperty(name = "手机")
    private String phone;

    @FieldSearch(value = "`email`")
    @ApiModelProperty(name = "邮箱")
    private String email;

}
