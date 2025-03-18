package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "新增箱单入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceContainerSearchDTO extends GenericDTO {

    @NeedValue(message = "箱号发票主键不能为空")
    @ApiModelProperty(name = "箱号发票主键")
    private Long packingListInvoiceId;

    @NeedValue(message = "箱号不能为空")
    @ApiModelProperty(name = "箱号")
    private String code;

}
