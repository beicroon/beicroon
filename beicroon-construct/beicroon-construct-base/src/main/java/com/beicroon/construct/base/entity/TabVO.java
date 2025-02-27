package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import com.beicroon.construct.enums.BooleanEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TabVO implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "排序")
    private Integer sort;

    @ApiModelProperty(name = "数量")
    private Long count;

    @ApiModelProperty(name = "颜色")
    private String color;

    @ApiModelProperty(name = "是否有子集")
    private String childrenFlag = BooleanEnums.FALSE.getCode();

    public TabVO(String code, String name, String childrenFlag) {
        this.code = code;

        this.name = name;

        this.childrenFlag = childrenFlag;
    }

    public TabVO(String code, String name, Long count, String childrenFlag) {
        this.code = code;

        this.name = name;

        this.count = count;

        this.childrenFlag = childrenFlag;
    }

}
