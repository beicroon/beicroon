package com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.vo;

import com.beicroon.construct.annotation.ApiModel;
import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.base.entity.GenericVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@ApiModel(name = "查询码单基础出参")
@EqualsAndHashCode(callSuper = true)
public class RonmioPackingListInvoiceRecordBaseVO extends GenericVO {

    @ApiModelProperty(name = "箱单发票主键")
    private Long packingListInvoiceId;

    @ApiModelProperty(name = "箱单发票单号")
    private String packingListInvoiceCode;

    @ApiModelProperty(name = "箱单主键")
    private Long containerId;

    @ApiModelProperty(name = "箱号")
    private String containerCode;

    @ApiModelProperty(name = "收货人")
    private String receiveName;

    @ApiModelProperty(name = "等级")
    private String level;

    @ApiModelProperty(name = "厚度(mm)")
    private Integer thickness;

    @ApiModelProperty(name = "宽度(mm)")
    private Integer width;

    @ApiModelProperty(name = "长度(mm)")
    private Integer length;

    @ApiModelProperty(name = "商品名称")
    private String commodityName;

    @ApiModelProperty(name = "商品编码")
    private String commodityCode;

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

    @ApiModelProperty(name = "总金额(元)")
    private BigDecimal totalAmount;

    @ApiModelProperty(name = "目的地")
    private String destination;

    @ApiModelProperty(name = "备注")
    private String remark;

}
