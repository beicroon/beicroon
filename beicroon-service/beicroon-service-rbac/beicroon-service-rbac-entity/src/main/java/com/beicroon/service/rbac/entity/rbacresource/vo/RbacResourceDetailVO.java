package com.beicroon.service.rbac.entity.rbacresource.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源详情出参")
@EqualsAndHashCode(callSuper = true)
public class RbacResourceDetailVO extends RbacResourceBaseVO {

    @ApiModelProperty(name = "父级编码")
    private String parentCode;

    @ApiModelProperty(name = "父级名称")
    private String parentName;

}
