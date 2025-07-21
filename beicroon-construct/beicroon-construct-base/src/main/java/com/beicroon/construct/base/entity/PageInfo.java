package com.beicroon.construct.base.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(name = "page", description = "当前页数")
    private long page;

    @Schema(name = "size", description = "当前条数")
    private long size;

    @Schema(name = "total", description = "总条数")
    private long total;

    @Schema(name = "data", description = "数据")
    private List<T> data;

    public PageInfo(QueryDTO.PageInfo pageInfo) {
        this.data = new ArrayList<>();

        this.page = pageInfo.getPage();

        this.size = pageInfo.getSize();

        this.total = 0L;
    }

    public PageInfo(QueryDTO.PageInfo pageInfo, List<T> data) {
        this.data = data;

        this.page = pageInfo.getPage();

        this.size = pageInfo.getSize();

        this.total = 0L;
    }

    public boolean isEmpty() {
        return this.data == null || this.data.isEmpty();
    }

}
