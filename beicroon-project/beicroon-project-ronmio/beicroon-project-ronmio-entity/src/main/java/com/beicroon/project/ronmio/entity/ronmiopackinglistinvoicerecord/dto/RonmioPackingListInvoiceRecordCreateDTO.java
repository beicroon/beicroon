package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "新增码单入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceRecordCreateDTO extends GenericDTO {

    @ApiModelProperty(name = "等级")
    private String level;

    @ApiModelProperty(name = "厚度(mm)")
    private Integer thickness;

    @ApiModelProperty(name = "宽度(mm)")
    private Integer width;

    @ApiModelProperty(name = "长度(mm)")
    private Integer length;

    @ApiModelProperty(name = "件数")
    private BigDecimal count;

    @ApiModelProperty(name = "体积(m³)")
    private BigDecimal volume;

    @ApiModelProperty(name = "净重(kg)")
    private BigDecimal netWeight;

    @ApiModelProperty(name = "毛重(kg)")
    private BigDecimal grossWeight;

    @ApiModelProperty(name = "单价(元)")
    private BigDecimal unitPrice;

}
