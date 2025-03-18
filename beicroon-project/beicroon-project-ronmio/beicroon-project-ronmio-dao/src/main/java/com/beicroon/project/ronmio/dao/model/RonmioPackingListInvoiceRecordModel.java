package com.beicroon.project.ronmio.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`ronmio_packing_list_invoice_record_relation`")
public class RonmioPackingListInvoiceRecordModel extends GenericModel {

    @TableField(name = "`packing_list_invoice_id`")
    @ApiModelProperty(name = "箱单发票主键")
    private Long packingListInvoiceId;

    @TableField(name = "`packing_list_invoice_code`")
    @ApiModelProperty(name = "箱单发票单号")
    private String packingListInvoiceCode;

    @TableField(name = "`container_id`")
    @ApiModelProperty(name = "箱单主键")
    private Long containerId;

    @TableField(name = "`container_code`")
    @ApiModelProperty(name = "箱号")
    private String containerCode;

    @TableField(name = "`receive_name`")
    @ApiModelProperty(name = "收货人")
    private String receiveName;

    @TableField(name = "`level`")
    @ApiModelProperty(name = "等级")
    private String level;

    @TableField(name = "`thickness`")
    @ApiModelProperty(name = "厚度(mm)")
    private Integer thickness;

    @TableField(name = "`width`")
    @ApiModelProperty(name = "宽度(mm)")
    private Integer width;

    @TableField(name = "`length`")
    @ApiModelProperty(name = "长度(mm)")
    private Integer length;

    @TableField(name = "`commodity_name`")
    @ApiModelProperty(name = "商品名称")
    private String commodityName;

    @TableField(name = "`commodity_code`")
    @ApiModelProperty(name = "商品编码")
    private String commodityCode;

    @TableField(name = "`count`")
    @ApiModelProperty(name = "件数")
    private BigDecimal count;

    @TableField(name = "`volume`")
    @ApiModelProperty(name = "体积(m³)")
    private BigDecimal volume;

    @TableField(name = "`net_weight`")
    @ApiModelProperty(name = "净重(kg)")
    private BigDecimal netWeight;

    @TableField(name = "`gross_weight`")
    @ApiModelProperty(name = "毛重(kg)")
    private BigDecimal grossWeight;

    @TableField(name = "`unit_price`")
    @ApiModelProperty(name = "单价(元)")
    private BigDecimal unitPrice;

    @TableField(name = "`total_amount`")
    @ApiModelProperty(name = "总金额(元)")
    private BigDecimal totalAmount;

    @TableField(name = "`destination`")
    @ApiModelProperty(name = "目的地")
    private String destination;

    @TableField(name = "`remark`")
    @ApiModelProperty(name = "备注")
    private String remark;

}
