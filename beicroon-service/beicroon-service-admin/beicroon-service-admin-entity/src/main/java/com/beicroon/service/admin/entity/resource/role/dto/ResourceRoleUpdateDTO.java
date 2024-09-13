package com.beicroon.service.admin.entity.resource.role.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "修改资源角色入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleUpdateDTO extends UpdateDTO {
    
    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "备注")
    private String remark;

    @ApiModelProperty(name = "是否超级管理员")
    private String superAdminFlag;

}
