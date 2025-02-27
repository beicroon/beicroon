package com.beicroon.service.ronmio.entity.ronmiopackinglistinvoice.dto;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "查询箱单发票入参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceQueryDTO extends QueryDTO {

    @FieldSearch(name = "`code`")
    @ApiModelProperty(name = "箱单单号")
    private String code;

    @FieldSearch(name = "`customer_id`")
    @ApiModelProperty(name = "客户主键")
    private Long customerId;

    @FieldSearch(name = "`customer_name`")
    @ApiModelProperty(name = "客户名称")
    private String customerName;

    @FieldSearch(name = "`train_code`")
    @ApiModelProperty(name = "班列号")
    private String trainCode;

    @FieldSearch(name = "`container_code`")
    @ApiModelProperty(name = "货柜号")
    private String containerCode;

    @FieldSearch(name = "`seller_name`")
    @ApiModelProperty(name = "卖方名称")
    private String sellerName;

    @FieldSearch(name = "`seller_addr`")
    @ApiModelProperty(name = "卖方地址")
    private String sellerAddr;

    @FieldSearch(name = "`buyer_name`")
    @ApiModelProperty(name = "买方名称")
    private String buyerName;

    @FieldSearch(name = "`buyer_addr`")
    @ApiModelProperty(name = "买方地址")
    private String buyerAddr;

    @FieldSearch(name = "`contract_code`")
    @ApiModelProperty(name = "合同号")
    private String contractCode;

    @FieldSearch(name = "`origin_country`")
    @ApiModelProperty(name = "原产国")
    private String originCountry;

    @FieldSearch(name = "`price_term`")
    @ApiModelProperty(name = "价格条款")
    private String priceTerm;

    @FieldSearch(name = "`box_type`")
    @ApiModelProperty(name = "箱型")
    private String boxType;

    @FieldSearch(name = "`total_amount`")
    @ApiModelProperty(name = "总金额")
    private BigDecimal totalAmount;

    @FieldSearch(name = "`total_volume`")
    @ApiModelProperty(name = "总体积")
    private BigDecimal totalVolume;

    @FieldSearch(name = "`total_net_weight`")
    @ApiModelProperty(name = "总净重")
    private BigDecimal totalNetWeight;

    @FieldSearch(name = "`total_gross_weight`")
    @ApiModelProperty(name = "总毛重")
    private BigDecimal totalGrossWeight;

    @FieldSearch(name = "`remark`")
    @ApiModelProperty(name = "备注")
    private String remark;

    @FieldSearch(name = "`total_amount_calculation`")
    @ApiModelProperty(name = "总价计算方式")
    private String totalAmountCalculation;

    @FieldSearch(name = "`net_weight_fractional`")
    @ApiModelProperty(name = "净重小数位数")
    private String netWeightFractional;

    @FieldSearch(name = "`gross_weight_fractional`")
    @ApiModelProperty(name = "毛重小数位数")
    private String grossWeightFractional;

}
