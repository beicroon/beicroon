package com.beicroon.service.admin.entity.account.admin.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(name = "修改后台账号入参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminUpdateDTO extends UpdateDTO {
    
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

    @ApiModelProperty(name = "删除时间")
    private LocalDateTime deletedAt;

    @ApiModelProperty(name = "删除人主键")
    private Long deleterId;

    @ApiModelProperty(name = "删除人编码")
    private String deleterCode;

    @ApiModelProperty(name = "删除人昵称")
    private String deleterName;

}
