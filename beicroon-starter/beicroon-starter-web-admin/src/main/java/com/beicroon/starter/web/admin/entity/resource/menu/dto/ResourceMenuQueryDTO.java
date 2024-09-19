package com.beicroon.starter.web.admin.entity.resource.menu.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.annotation.KeywordsSearch;
import com.beicroon.construct.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源菜单入参")
@EqualsAndHashCode(callSuper = true)
@KeywordsSearch(name = {"`code`", "`name`"})
public class ResourceMenuQueryDTO extends QueryDTO {

    @FieldSearch(name = "`parent_id`")
    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @FieldSearch(name = "`parent_code`")
    @ApiModelProperty(name = "父级编码")
    private String parentCode;

    @FieldSearch(name = "`parent_name`")
    @ApiModelProperty(name = "父级名称")
    private String parentName;

    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @FieldSearch(name = "`path`")
    @ApiModelProperty(name = "路由")
    private String path;

    @FieldSearch(name = "`sorting`")
    @ApiModelProperty(name = "排序")
    private Integer sorting;

}
