package com.beicroon.service.rbac.entity.adminaccount.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "查询账号详情出参")
@EqualsAndHashCode(callSuper = true)
public class AdminAccountDetailVO extends AdminAccountBaseVO {

    @ApiModelProperty(name = "角色主键列表")
    private List<Long> roleIds;

}
