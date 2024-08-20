package com.beicroon.starter.web.admin.entity.account.admin.vo;

import com.beicroon.construct.annotation.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询后台账号分页出参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminPageVO extends AccountAdminBaseVO {

}
