package com.beicroon.service.admin.entity.account.admin.vo;

import com.beicroon.construct.annotation.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询后台账号详情出参")
@EqualsAndHashCode(callSuper = true)
public class AccountAdminDetailVO extends AccountAdminBaseVO {

}
