package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.base.entity.GenericDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.dto.RonmioPackingListInvoiceRecordCreateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(name = "新增箱单入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceContainerAppendDTO extends GenericDTO {

    @ApiModelProperty(name = "箱号主键")
    private Long id;

    @NeedValue(message = "箱号发票主键不能为空")
    @ApiModelProperty(name = "箱号发票主键")
    private Long packingListInvoiceId;

    @NeedValue(message = "箱号不能为空")
    @ApiModelProperty(name = "箱号")
    private String code;

    @ApiModelProperty(name = "净重(kg)")
    private BigDecimal netWeight;

    @ApiModelProperty(name = "毛重(kg)")
    private BigDecimal grossWeight;

    @ApiModelProperty(name = "码单列表")
    private List<RonmioPackingListInvoiceRecordCreateDTO> records;

}
