package com.beicroon.service.rbac.entity.rbacresource.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(name = "查询资源树状出参")
@EqualsAndHashCode(callSuper = true)
public class RbacResourceTreeVO extends RbacResourceBaseVO {

    @ApiModelProperty(name = "子级列表")
    private List<RbacResourceTreeVO> children = new ArrayList<>();

}
