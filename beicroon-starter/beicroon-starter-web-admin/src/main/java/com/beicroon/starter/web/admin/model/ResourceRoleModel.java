package com.beicroon.starter.web.admin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.starter.mysql.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "资源角色")
@TableName("`admin_resource_role`")
@EqualsAndHashCode(callSuper = true)
public class ResourceRoleModel extends GenericModel {

    @TableField(value = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(value = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @TableField(value = "`remark`")
    @ApiModelProperty(name = "备注")
    private String remark;

    @TableField(value = "`root_flag`")
    @ApiModelProperty(name = "是否超级管理员")
    private String superAdminFlag;

}
