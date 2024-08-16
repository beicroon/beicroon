package com.beicroon.service.admin.dao.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(name = "资源菜单")
@TableName("`admin_resource_menu`")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuModel extends GenericModel {
    
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
    @ApiModelProperty(name = "路径")
    private String path;

    @TableField(value = "`deleted_at`")
    @ApiModelProperty(name = "删除时间")
    private LocalDateTime deletedAt;

    @TableField(value = "`deleter_id`")
    @ApiModelProperty(name = "删除人主键")
    private Long deleterId;

    @TableField(value = "`deleter_code`")
    @ApiModelProperty(name = "删除人编码")
    private String deleterCode;

    @TableField(value = "`deleter_name`")
    @ApiModelProperty(name = "删除人昵称")
    private String deleterName;

}
