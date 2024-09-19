package com.beicroon.starter.web.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.DisableModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "资源菜单")
@TableName("`admin_resource_menu`")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuModel extends DisableModel {

    @TableField(value = "`parent_id`")
    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @TableField(value = "`parent_code`")
    @ApiModelProperty(name = "父级编码")
    private String parentCode;

    @TableField(value = "`parent_name`")
    @ApiModelProperty(name = "父级名称")
    private String parentName;

    @TableField(value = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(value = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @TableField(value = "`path`")
    @ApiModelProperty(name = "路由")
    private String path;

    @TableField(value = "`sorting`")
    @ApiModelProperty(name = "排序")
    private Integer sorting;

}
