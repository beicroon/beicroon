package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.annotation.NeedValue;
import com.beicroon.construct.base.entity.IdDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(name = "编辑箱单发票入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceUpdateDTO extends IdDTO {

    @NeedValue(message = "客户名称不能为空")
    @ApiModelProperty(name = "客户名称")
    private String customerName;

    @ApiModelProperty(name = "班列号")
    private String trainCode;

    @ApiModelProperty(name = "货柜号")
    private String containerCode;

    @ApiModelProperty(name = "卖方名称")
    private String sellerName;

    @ApiModelProperty(name = "卖方地址")
    private String sellerAddr;

    @ApiModelProperty(name = "买方名称")
    private String buyerName;

    @ApiModelProperty(name = "买方地址")
    private String buyerAddr;

    @ApiModelProperty(name = "合同号")
    private String contractCode;

    @ApiModelProperty(name = "原产国")
    private String originCountry;

    @ApiModelProperty(name = "价格条款")
    private String priceTerm;

    @ApiModelProperty(name = "箱型")
    private String boxType;

    @ApiModelProperty(name = "备注")
    private String remark;

    @ApiModelProperty(name = "总价计算方式")
    private String totalAmountCalculation;

    @ApiModelProperty(name = "净重小数位数")
    private String netWeightFractional;

    @ApiModelProperty(name = "毛重小数位数")
    private String grossWeightFractional;

}
