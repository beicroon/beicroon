package com.beicroon.service.ronmio.entity.ronmiopackinglistinvoicerecord.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "查询码单详细出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceRecordDetailVO extends RonmioPackingListInvoiceRecordBaseVO {

    @ApiModelProperty(name = "规格")
    private String specification;

}
