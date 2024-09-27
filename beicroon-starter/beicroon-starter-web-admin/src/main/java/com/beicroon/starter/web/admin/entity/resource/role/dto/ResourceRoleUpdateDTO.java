package com.beicroon.starter.web.admin.entity.resource.role.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTO;
import com.beicroon.construct.validator.annotation.NeedValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "修改资源角色入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleUpdateDTO extends UpdateDTO {

    @NeedValue(message = "编码[code]不能为空")
    @ApiModelProperty(name = "编码")
    private String code;

    @NeedValue(message = "名称[name]不能为空")
    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "备注")
    private String remark;

}
