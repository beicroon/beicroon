package com.beicroon.service.ronmio.entity.ronmiocustomer.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "编辑客户入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioCustomerUpdateDTO extends IdDTO {

    @ApiModelProperty(name = "客户名称")
    private String name;

    @ApiModelProperty(name = "联系电话")
    private String phone;

}
