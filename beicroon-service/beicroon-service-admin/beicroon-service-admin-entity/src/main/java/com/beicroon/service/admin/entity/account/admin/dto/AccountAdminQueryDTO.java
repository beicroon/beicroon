package com.beicroon.service.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(name = "查询后台账号入参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminQueryDTO extends QueryDTO {
    
    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(name = "`username`")
    @ApiModelProperty(name = "账号")
    private String username;

    @FieldSearch(name = "`password`")
    @ApiModelProperty(name = "密码")
    private String password;

    @FieldSearch(name = "`nickname`")
    @ApiModelProperty(name = "昵称")
    private String nickname;

    @FieldSearch(name = "`phone`")
    @ApiModelProperty(name = "电话")
    private String phone;

    @FieldSearch(name = "`email`")
    @ApiModelProperty(name = "邮箱")
    private String email;

    @FieldSearch(name = "`deleted_at`")
    @ApiModelProperty(name = "删除时间")
    private LocalDateTime deletedAt;

    @FieldSearch(name = "`deleter_id`")
    @ApiModelProperty(name = "删除人主键")
    private Long deleterId;

    @FieldSearch(name = "`deleter_code`")
    @ApiModelProperty(name = "删除人编码")
    private String deleterCode;

    @FieldSearch(name = "`deleter_name`")
    @ApiModelProperty(name = "删除人昵称")
    private String deleterName;

}
