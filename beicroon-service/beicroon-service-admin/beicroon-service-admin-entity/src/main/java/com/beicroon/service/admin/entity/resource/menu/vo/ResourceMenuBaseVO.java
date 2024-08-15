package com.beicroon.service.admin.entity.resource.menu.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源菜单基础出参")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuBaseVO extends GenericVO {
    
    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

}
