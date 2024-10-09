package com.beicroon.starter.web.admin.entity.resource.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTO;
import com.beicroon.construct.validator.annotation.NeedValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "修改资源菜单入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuUpdateDTO extends UpdateDTO {

    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @NeedValue(message = "名称[name]不能为空")
    @ApiModelProperty(name = "名称")
    private String name;

    @NeedValue(message = "路由[path]不能为空")
    @ApiModelProperty(name = "路由")
    private String path;

    @ApiModelProperty(name = "排序")
    private Integer sorting;

}
