package com.beicroon.service.admin.entity.adminresource.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增资源入参")
@EqualsAndHashCode(callSuper = true)
public class AdminResourceCreateDTO extends GenericDTO {

    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "路由")
    private String path;

}
