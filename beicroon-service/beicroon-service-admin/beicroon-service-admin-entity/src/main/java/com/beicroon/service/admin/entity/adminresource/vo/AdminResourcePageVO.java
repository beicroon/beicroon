package com.beicroon.service.admin.entity.adminresource.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询资源分页出参")
@EqualsAndHashCode(callSuper = true)
public class AdminResourcePageVO extends AdminResourceBaseVO {

    @ApiModelProperty(name = "父级编码")
    private String parentCode;

    @ApiModelProperty(name = "父级名称")
    private String parentName;

}
