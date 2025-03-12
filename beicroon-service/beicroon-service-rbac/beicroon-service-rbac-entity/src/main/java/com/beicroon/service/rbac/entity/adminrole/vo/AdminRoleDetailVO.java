package com.beicroon.service.rbac.entity.adminrole.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "查询角色详情出参")
@EqualsAndHashCode(callSuper = true)
public class AdminRoleDetailVO extends AdminRoleBaseVO {

    @ApiModelProperty(name = "资源主键列表")
    private List<Long> resourceIds;

}
