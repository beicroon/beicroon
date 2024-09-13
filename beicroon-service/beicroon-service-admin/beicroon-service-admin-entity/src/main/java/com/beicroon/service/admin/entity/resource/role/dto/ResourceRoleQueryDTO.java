package com.beicroon.service.admin.entity.resource.role.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源角色入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleQueryDTO extends QueryDTO {
    
    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @FieldSearch(name = "`remark`")
    @ApiModelProperty(name = "备注")
    private String remark;

    @FieldSearch(name = "`super_admin_flag`")
    @ApiModelProperty(name = "是否超级管理员")
    private String superAdminFlag;

}
