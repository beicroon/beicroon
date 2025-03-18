package com.beicroon.project.ronmio.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`ronmio_packing_list_invoice`")
public class RonmioPackingListInvoiceModel extends GenericModel {

    @TableField(name = "`code`")
    @ApiModelProperty(name = "箱单单号")
    private String code;

    @TableField(name = "`customer_id`")
    @ApiModelProperty(name = "客户主键")
    private Long customerId;

    @TableField(name = "`customer_name`")
    @ApiModelProperty(name = "客户名称")
    private String customerName;

    @TableField(name = "`train_code`")
    @ApiModelProperty(name = "班列号")
    private String trainCode;

    @TableField(name = "`container_code`")
    @ApiModelProperty(name = "货柜号")
    private String containerCode;

    @TableField(name = "`seller_name`")
    @ApiModelProperty(name = "卖方名称")
    private String sellerName;

    @TableField(name = "`seller_addr`")
    @ApiModelProperty(name = "卖方地址")
    private String sellerAddr;

    @TableField(name = "`buyer_name`")
    @ApiModelProperty(name = "买方名称")
    private String buyerName;

    @TableField(name = "`buyer_addr`")
    @ApiModelProperty(name = "买方地址")
    private String buyerAddr;

    @TableField(name = "`contract_code`")
    @ApiModelProperty(name = "合同号")
    private String contractCode;

    @TableField(name = "`origin_country`")
    @ApiModelProperty(name = "原产国")
    private String originCountry;

    @TableField(name = "`price_term`")
    @ApiModelProperty(name = "价格条款")
    private String priceTerm;

    @TableField(name = "`box_type`")
    @ApiModelProperty(name = "箱型")
    private String boxType;

    @TableField(name = "`remark`")
    @ApiModelProperty(name = "备注")
    private String remark;

    @TableField(name = "`total_amount_calculation`")
    @ApiModelProperty(name = "总价计算方式")
    private String totalAmountCalculation;

    @TableField(name = "`net_weight_fractional`")
    @ApiModelProperty(name = "净重小数位数")
    private String netWeightFractional;

    @TableField(name = "`gross_weight_fractional`")
    @ApiModelProperty(name = "毛重小数位数")
    private String grossWeightFractional;

}
