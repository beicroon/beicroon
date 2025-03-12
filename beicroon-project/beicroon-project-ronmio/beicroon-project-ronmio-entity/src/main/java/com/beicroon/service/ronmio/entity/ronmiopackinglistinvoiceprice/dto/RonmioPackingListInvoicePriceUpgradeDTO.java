package com.beicroon.service.ronmio.entity.ronmiopackinglistinvoiceprice.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "更新单价入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoicePriceUpgradeDTO extends GenericDTO {

    @ApiModelProperty(name = "等级")
    private String level;

    @ApiModelProperty(name = "厚度(mm)")
    private Integer thickness;

    @ApiModelProperty(name = "宽度(mm)")
    private Integer width;

    @ApiModelProperty(name = "最小长度(mm)")
    private Integer minLength;

    @ApiModelProperty(name = "最大长度(mm)")
    private Integer maxLength;

    @ApiModelProperty(name = "单价(元)")
    private BigDecimal unitPrice;

}
