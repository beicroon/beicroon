package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerDetailVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(name = "查询箱单发票详情出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceDetailVO extends RonmioPackingListInvoiceBaseVO {

    @ApiModelProperty(name = "总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(name = "总体积")
    private BigDecimal totalVolume;

    @ApiModelProperty(name = "总净重")
    private BigDecimal totalNetWeight;

    @ApiModelProperty(name = "总毛重")
    private BigDecimal totalGrossWeight;

    @ApiModelProperty(name = "箱单列表")
    private List<RonmioPackingListInvoiceContainerDetailVO> containers;

}
