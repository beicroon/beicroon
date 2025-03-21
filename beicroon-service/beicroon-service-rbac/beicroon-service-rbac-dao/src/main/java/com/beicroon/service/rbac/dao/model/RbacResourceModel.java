package com.beicroon.service.rbac.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`rbac_resource`")
public class RbacResourceModel extends GenericModel {

    @TableField(name = "`parent_id`")
    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @TableField(name = "`code`")
    @ApiModelProperty(name = "编码")
    private String code;

    @TableField(name = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;

    @TableField(name = "`path`")
    @ApiModelProperty(name = "路由")
    private String path;

}
