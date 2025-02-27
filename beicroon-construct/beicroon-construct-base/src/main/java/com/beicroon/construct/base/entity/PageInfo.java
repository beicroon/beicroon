package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PageInfo<T> implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "当前页数")
    private long page;

    @ApiModelProperty(name = "当前条数")
    private long size;

    @ApiModelProperty(name = "总条数")
    private long total;

    @ApiModelProperty(name = "数据")
    private List<T> data;

    public PageInfo(QueryDTO.PageInfo pageInfo) {
        this.data = new ArrayList<>();

        this.page = pageInfo.getPage();

        this.size = pageInfo.getSize();

        this.total = 0L;
    }

}
