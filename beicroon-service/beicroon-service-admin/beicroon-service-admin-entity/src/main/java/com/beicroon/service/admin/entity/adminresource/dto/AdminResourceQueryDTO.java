package com.beicroon.service.admin.entity.adminresource.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源入参")
@EqualsAndHashCode(callSuper = true)
public class AdminResourceQueryDTO extends QueryDTO {

    @FieldSearch(name = "`parent_id`")
    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @FieldSearch(name = "`path`")
    @ApiModelProperty(name = "路由")
    private String path;

}
