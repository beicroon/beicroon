package com.beicroon.service.admin.dao.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "资源菜单")
@TableName("`admin_resource_menu`")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuModel extends GenericModel {
    
    @TableField(value = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(value = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

}
