package com.beicroon.service.admin.entity.resource.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.UpdateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "修改资源菜单入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuUpdateDTO extends UpdateDTO {
    
    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

}
