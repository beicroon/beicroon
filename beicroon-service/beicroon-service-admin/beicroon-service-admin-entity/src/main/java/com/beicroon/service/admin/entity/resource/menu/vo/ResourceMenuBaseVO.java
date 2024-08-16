package com.beicroon.service.admin.entity.resource.menu.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@ApiModel(name = "查询资源菜单基础出参")
@EqualsAndHashCode(callSuper = true)
public class ResourceMenuBaseVO extends GenericVO {
    
    @ApiModelProperty(name = "父级主键")
    private Long parentId;

    @ApiModelProperty(name = "父级编码")
    private String parentCode;

    @ApiModelProperty(name = "父级名称")
    private String parentName;

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "路径")
    private String path;

    @ApiModelProperty(name = "删除时间")
    private LocalDateTime deletedAt;

    @ApiModelProperty(name = "删除人主键")
    private Long deleterId;

    @ApiModelProperty(name = "删除人编码")
    private String deleterCode;

    @ApiModelProperty(name = "删除人昵称")
    private String deleterName;

}
