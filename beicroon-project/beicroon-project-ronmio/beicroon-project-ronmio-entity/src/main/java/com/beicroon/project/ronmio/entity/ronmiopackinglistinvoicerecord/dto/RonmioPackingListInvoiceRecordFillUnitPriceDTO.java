package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.base.entity.GenericDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoiceprice.dto.RonmioPackingListInvoicePriceUpgradeDTO;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "更新单价入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceRecordFillUnitPriceDTO extends GenericDTO {

    @NeedValue(message = "箱单发票主键不能为空")
    @ApiModelProperty(name = "箱单发票主键")
    private Long packingListInvoiceId;

    @Valid
    @ApiModelProperty(name = "规格列表")
    private List<RonmioPackingListInvoicePriceUpgradeDTO> groups;

}
