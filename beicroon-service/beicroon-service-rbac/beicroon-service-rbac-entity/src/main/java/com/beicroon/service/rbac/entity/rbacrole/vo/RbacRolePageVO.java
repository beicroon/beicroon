package com.beicroon.service.rbac.entity.rbacrole.vo;

import com.beicroon.construct.annotation.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询角色分页出参")
@EqualsAndHashCode(callSuper = true)
public class RbacRolePageVO extends RbacRoleBaseVO {

}
