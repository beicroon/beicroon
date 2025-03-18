package com.beicroon.project.ronmio.entity.ronmiocustomer.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询客户入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioCustomerQueryDTO extends QueryDTO {

    @FieldSearch(name = "`name`")
    @ApiModelProperty(name = "客户名称")
    private String name;

    @FieldSearch(name = "`phone`")
    @ApiModelProperty(name = "联系电话")
    private String phone;

}
