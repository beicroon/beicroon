package com.beicroon.service.admin.entity.adminaccount.vo;

import com.beicroon.construct.annotation.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询账号分页出参")
@EqualsAndHashCode(callSuper = true)
public class AdminAccountPageVO extends AdminAccountBaseVO {

}
