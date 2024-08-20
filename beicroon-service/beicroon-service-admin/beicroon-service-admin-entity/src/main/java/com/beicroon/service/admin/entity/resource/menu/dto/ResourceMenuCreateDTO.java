package com.beicroon.service.admin.entity.resource.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.CreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增资源菜单入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuCreateDTO extends CreateDTO {
    
    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @ApiModelProperty(name = "父级编码")
    private String parentCode;

    @ApiModelProperty(name = "父级名称")
    private String parentName;

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "路径")
    private String path;

    @ApiModelProperty(name = "排序")
    private Integer sorting;

}
