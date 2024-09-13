package com.beicroon.service.admin.entity.resource.role.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源角色基础出参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleBaseVO extends GenericVO {
    
    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "备注")
    private String remark;

    @ApiModelProperty(name = "是否超级管理员")
    private String superAdminFlag;

}
