package com.beicroon.service.admin.entity.resource.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源菜单入参")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuQueryDTO extends QueryDTO {
    
    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

}
