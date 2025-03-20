package com.beicroon.service.rbac.entity.rbacrole.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询角色基础出参")
@EqualsAndHashCode(callSuper = true)
public class RbacRoleBaseVO extends GenericVO {

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

}
