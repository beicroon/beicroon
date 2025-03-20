package com.beicroon.service.rbac.entity.rbacroleresource.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询角色资源关系基础出参")
@EqualsAndHashCode(callSuper = true)
public class RbacRoleResourceBaseVO extends GenericVO {

    @ApiModelProperty(name = "角色主键")
    private Long roleId;

    @ApiModelProperty(name = "资源主键")
    private Long resourceId;

}
