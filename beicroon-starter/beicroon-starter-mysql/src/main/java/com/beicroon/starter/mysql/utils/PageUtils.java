package com.beicroon.starter.mysql.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.entity.PageInfo;
import com.beicroon.construct.entity.QueryDTO;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;

import java.util.List;
import java.util.function.Function;

public final class PageUtils {

    private PageUtils() {

    }

    public static <T, Q extends QueryDTO> Page<T> build(Q dto) {
        return new Page<>(dto.getPageNumOrDefault(), dto.getPageSizeOrDefault());
    }

    public static <T, R> List<R> result(List<T> data, Function<T, R> consumer) {
        return ListUtils.toList(data, consumer);
    }

    public static <T, R> PageInfo<R> result(Page<T> page, Function<T, R> consumer) {
        PageInfo<R> pageInfo = new PageInfo<>();

        List<R> data;

        if (consumer != null) {
            data = PageUtils.result(page.getRecords(), consumer);
        } else {
            data = EmptyUtils.emptyList();
        }

        pageInfo.setData(data);
        pageInfo.setPageNum(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setTotalSize(page.getTotal());

        return pageInfo;
    }

}
