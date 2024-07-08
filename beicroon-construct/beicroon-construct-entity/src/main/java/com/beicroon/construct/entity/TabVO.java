package com.beicroon.construct.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TabVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "编码")
    private String code;

    @ApiModelProperty(name = "名称")
    private String name;

    @ApiModelProperty(name = "排序")
    private Integer sort;

    @ApiModelProperty(name = "数量")
    private Long count;

    @ApiModelProperty(name = "子集")
    private List<TabVO> children;

    public TabVO(String code, String name) {
        this.code = code;

        this.name = name;
    }

    public TabVO(String code, String name, Long count) {
        this.code = code;

        this.name = name;

        this.count = count;
    }

}
