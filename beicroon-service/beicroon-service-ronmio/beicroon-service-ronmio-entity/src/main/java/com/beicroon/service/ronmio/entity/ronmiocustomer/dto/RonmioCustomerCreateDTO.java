package com.beicroon.service.ronmio.entity.ronmiocustomer.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增客户入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioCustomerCreateDTO extends GenericDTO {

    @ApiModelProperty(name = "客户名称")
    private String name;

    @ApiModelProperty(name = "联系电话")
    private String phone;

}
