package com.beicroon.service.rbac.entity.adminaccountrole.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询账号角色关系基础出参")
@EqualsAndHashCode(callSuper = true)
public class AdminAccountRoleBaseVO extends GenericVO {

    @ApiModelProperty(name = "账号主键")
    private Long accountId;

    @ApiModelProperty(name = "角色主键")
    private Long roleId;

}
