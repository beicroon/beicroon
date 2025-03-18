package com.beicroon.project.ronmio.dao.model;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.annotation.TableField;
import com.beicroon.construct.annotation.TableName;
import com.beicroon.construct.database.model.GenericModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("`ronmio_customer`")
public class RonmioCustomerModel extends GenericModel {

    @TableField(name = "`name`")
    @ApiModelProperty(name = "客户名称")
    private String name;

    @TableField(name = "`phone`")
    @ApiModelProperty(name = "联系电话")
    private String phone;

}
