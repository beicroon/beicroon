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
@TableName("`ronmio_packing_list_invoice_container_relation`")
public class RonmioPackingListInvoiceContainerModel extends GenericModel {

    @TableField(name = "`packing_list_invoice_id`")
    @ApiModelProperty(name = "箱单发票主键")
    private Long packingListInvoiceId;

    @TableField(name = "`packing_list_invoice_code`")
    @ApiModelProperty(name = "箱单发票单号")
    private String packingListInvoiceCode;

    @TableField(name = "`code`")
    @ApiModelProperty(name = "箱号")
    private String code;

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

}
