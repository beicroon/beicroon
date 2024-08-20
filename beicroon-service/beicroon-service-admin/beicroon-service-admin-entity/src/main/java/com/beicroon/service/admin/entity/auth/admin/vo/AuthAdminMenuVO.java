package com.beicroon.service.admin.entity.auth.admin.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.service.admin.entity.resource.menu.vo.ResourceMenuBaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(name = "认证菜单出参")
@EqualsAndHashCode(callSuper = true)
public class AuthAdminMenuVO extends ResourceMenuBaseVO {

    @ApiModelProperty(name = "菜单子集")
    private List<AuthAdminMenuVO> children = new ArrayList<>();

}
