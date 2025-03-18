package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.vo.RonmioPackingListInvoiceRecordBaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@ApiModel(name = "查询箱单详细出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceContainerSearchVO extends RonmioPackingListInvoiceContainerBaseVO {

    @ApiModelProperty(name = "码单列表")
    private List<RonmioPackingListInvoiceRecordBaseVO> records;

}
