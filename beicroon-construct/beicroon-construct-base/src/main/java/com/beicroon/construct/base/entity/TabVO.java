package com.beicroon.construct.base.entity;

import com.beicroon.construct.enums.BooleanEnums;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(name = "code", description = "编码")
    private String code;

    @Schema(name = "name", description = "名称")
    private String name;

    @Schema(name = "sort", description = "排序")
    private Integer sort;

    @Schema(name = "count", description = "数量")
    private Long count;

    @Schema(name = "color", description = "颜色")
    private String color;

    @Schema(name = "childrenFlag", description = "是否有子集")
    private String childrenFlag = BooleanEnums.FALSE.getCode();

    public TabVO(Integer sort, String code, String name, String childrenFlag) {
        this.sort = sort;

        this.code = code;

        this.name = name;

        this.childrenFlag = childrenFlag;
    }

    public TabVO(Integer sort, String code, String name, Long count, String childrenFlag) {
        this.sort = sort;

        this.code = code;

        this.name = name;

        this.count = count;

        this.childrenFlag = childrenFlag;
    }

}
