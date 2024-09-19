package com.beicroon.starter.web.admin.entity.resource.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.NeedValue;
import com.beicroon.construct.entity.CreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增资源菜单入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuCreateDTO extends CreateDTO {

    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @NeedValue(message = "编码[code]不能为空")
    @ApiModelProperty(name = "编码")
    private String code;

    @NeedValue(message = "名称[name]不能为空")
    @ApiModelProperty(name = "名称")
    private String name;

    @NeedValue(message = "路由[path]不能为空")
    @ApiModelProperty(name = "路由")
    private String path;

    @ApiModelProperty(name = "排序")
    private Integer sorting;

}
