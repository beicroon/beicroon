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
@TableName("`ronmio_packing_list_invoice_price_relation`")
public class RonmioPackingListInvoicePriceModel extends GenericModel {

    @TableField(name = "`packing_list_invoice_id`")
    @ApiModelProperty(name = "箱单发票主键")
    private Long packingListInvoiceId;

    @TableField(name = "`packing_list_invoice_code`")
    @ApiModelProperty(name = "箱单发票单号")
    private String packingListInvoiceCode;

    @TableField(name = "`level`")
    @ApiModelProperty(name = "等级")
    private String level;

    @TableField(name = "`thickness`")
    @ApiModelProperty(name = "厚度(mm)")
    private Integer thickness;

    @TableField(name = "`width`")
    @ApiModelProperty(name = "宽度(mm)")
    private Integer width;

    @TableField(name = "`min_length`")
    @ApiModelProperty(name = "最小长度(mm)")
    private Integer minLength;

    @TableField(name = "`max_length`")
    @ApiModelProperty(name = "最大长度(mm)")
    private Integer maxLength;

    @TableField(name = "`unit_price`")
    @ApiModelProperty(name = "单价(元)")
    private BigDecimal unitPrice;

}
