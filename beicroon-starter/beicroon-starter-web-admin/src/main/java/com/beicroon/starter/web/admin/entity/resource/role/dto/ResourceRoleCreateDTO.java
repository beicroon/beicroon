package com.beicroon.starter.web.admin.entity.resource.role.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增资源角色入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleCreateDTO extends CreateDTO {
    
    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "备注")
    private String remark;

}
