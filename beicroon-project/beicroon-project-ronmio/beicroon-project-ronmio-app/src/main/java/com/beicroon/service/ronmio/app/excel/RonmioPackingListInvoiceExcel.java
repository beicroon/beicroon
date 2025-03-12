package com.beicroon.service.ronmio.app.excel;

import com.beicroon.construct.excel.annotation.ExcelProperty;
import com.beicroon.construct.excel.annotation.ExcelWidth;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class RonmioPackingListInvoiceExcel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelWidth(value = 6)
    @ExcelProperty(name = "序号")
    private String index;

    @ExcelWidth(value = 20)
    @ExcelProperty(name = "箱号")
    private String container;

    @ExcelWidth(value = 28)
    @ExcelProperty(name = "收货人")
    private String receiveName;

    @ExcelWidth(value = 10)
    @ExcelProperty(name = "等级")
    private String level;

    @ExcelWidth(value = 24)
    @ExcelProperty(name = "规格")
    private String specification;

    @ExcelWidth(value = 18)
    @ExcelProperty(name = "商品名称")
    private String commodityName;

    @ExcelWidth(value = 13)
    @ExcelProperty(name = "商品编码")
    private String commodityCode;

    @ExcelWidth(value = 13)
    @ExcelProperty(name = "数量\r\n(件数)")
    private Integer count;

    @ExcelWidth(value = 9)
    @ExcelProperty(name = "数量\r\n(总件数)")
    private Integer totalCount;

    @ExcelWidth(value = 9)
    @ExcelProperty(name = "数量\r\n(立方米)")
    private BigDecimal volume;

    @ExcelWidth(value = 9)
    @ExcelProperty(name = "总数量\r\n(立方米)")
    private BigDecimal totalVolume;

    @ExcelWidth(value = 9)
    @ExcelProperty(name = "净重\r\n(千克)")
    private BigDecimal netWeight;

    @ExcelWidth(value = 9)
    @ExcelProperty(name = "总净重\r\n(千克)")
    private BigDecimal totalNetWeight;

    @ExcelWidth(value = 9)
    @ExcelProperty(name = "毛重\r\n(千克)")
    private BigDecimal grossWeight;

    @ExcelWidth(value = 9)
    @ExcelProperty(name = "总毛重\r\n(千克)")
    private BigDecimal totalGrossWeight;

    @ExcelWidth(value = 10)
    @ExcelProperty(name = "单价\r\n(人民币)")
    private BigDecimal unitPrice;

    @ExcelWidth(value = 10)
    @ExcelProperty(name = "货值\r\n(人民币)")
    private BigDecimal totalPrice;

    @ExcelWidth(value = 10)
    @ExcelProperty(name = "总货值\r\n(人民币)")
    private BigDecimal totalAmount;

    @ExcelWidth(value = 8)
    @ExcelProperty(name = "目的地")
    private String destination;

    @ExcelWidth(value = 8)
    @ExcelProperty(name = "备注")
    private String remark;

}
