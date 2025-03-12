package com.beicroon.service.ronmio.entity.ronmiopackinglistinvoiceprice.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "查询单价基础出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoicePriceBaseVO extends GenericVO {

    @ApiModelProperty(name = "箱单发票主键")
    private Long packingListInvoiceId;

    @ApiModelProperty(name = "箱单发票单号")
    private String packingListInvoiceCode;

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
