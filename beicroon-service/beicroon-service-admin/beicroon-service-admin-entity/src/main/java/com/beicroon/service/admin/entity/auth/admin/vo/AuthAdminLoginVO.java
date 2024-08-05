package com.beicroon.service.admin.entity.auth.admin.vo;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthAdminLoginVO extends BaseVO {

    @ApiModelProperty(name = "主键")
    private Long id;

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "昵称")
    private String nickname;

    @ApiModelProperty(name = "令牌")
    private String token;

}
