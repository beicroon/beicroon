package com.beicroon.project.ronmio.entity.ronmiocustomer.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询客户基础出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioCustomerBaseVO extends GenericVO {

    @ApiModelProperty(name = "客户名称")
    private String name;

    @ApiModelProperty(name = "联系电话")
    private String phone;

}
