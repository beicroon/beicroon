package com.beicroon.service.admin.entity.adminrole.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "编辑角色入参")
@EqualsAndHashCode(callSuper = true)
public class AdminRoleUpdateDTO extends IdDTO {

    @NeedValue(message = "编码不能为空")
    @ApiModelProperty(name = "编码")
    private String code;

    @NeedValue(message = "名称不能为空")
    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "资源主键列表")
    private List<Long> resourceIds;

}
