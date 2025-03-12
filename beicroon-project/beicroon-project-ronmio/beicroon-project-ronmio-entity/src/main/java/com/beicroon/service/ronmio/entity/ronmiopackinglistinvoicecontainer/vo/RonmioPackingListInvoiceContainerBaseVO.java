package com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "查询箱单基础出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceContainerBaseVO extends GenericVO {

    @ApiModelProperty(name = "箱单发票主键")
    private Long packingListInvoiceId;

    @ApiModelProperty(name = "箱单发票单号")
    private String packingListInvoiceCode;

    @ApiModelProperty(name = "箱号")
    private String code;

    @ApiModelProperty(name = "件数")
    private BigDecimal count;

    @ApiModelProperty(name = "体积(m³)")
    private BigDecimal volume;

    @ApiModelProperty(name = "净重(kg)")
    private BigDecimal netWeight;

    @ApiModelProperty(name = "毛重(kg)")
    private BigDecimal grossWeight;

}
