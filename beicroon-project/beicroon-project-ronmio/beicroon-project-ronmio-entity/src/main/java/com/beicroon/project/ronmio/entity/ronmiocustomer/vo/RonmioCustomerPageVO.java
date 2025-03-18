package com.beicroon.project.ronmio.entity.ronmiocustomer.vo;

import com.beicroon.construct.annotation.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询客户分页出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioCustomerPageVO extends RonmioCustomerBaseVO {

}
