package com.beicroon.construct.entity;

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
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "数据")
    private List<T> data;

    @ApiModelProperty(name = "当前页数")
    private long pageNum;

    @ApiModelProperty(name = "当前条数")
    private long pageSize;

    @ApiModelProperty(name = "总条数")
    private long totalSize;

    public PageInfo(QueryDTO dto) {
        this.data = new ArrayList<>();

        this.pageNum = dto.getPageNum();

        this.pageSize = dto.getPageSize();

        this.totalSize = 0L;
    }

}
