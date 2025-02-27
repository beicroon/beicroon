package com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicecontainer.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicerecord.vo.RonmioPackingListInvoiceRecordDetailVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(name = "查询箱单详细出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceContainerDetailVO extends RonmioPackingListInvoiceContainerBaseVO {

    @ApiModelProperty(name = "总金额(元)")
    private BigDecimal totalAmount;

    @ApiModelProperty(name = "码单列表")
    private List<RonmioPackingListInvoiceRecordDetailVO> records;

}
