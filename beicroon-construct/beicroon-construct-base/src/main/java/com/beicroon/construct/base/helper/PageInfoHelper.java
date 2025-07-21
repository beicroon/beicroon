package com.beicroon.construct.base.helper;

import com.beicroon.construct.base.entity.QueryDTO;

public final class PageInfoHelper {

    private PageInfoHelper() {

    }

    public static Long getPageOrDefault(QueryDTO.PageInfo pageInfo) {
        Integer page = pageInfo.getPage();

        if (page == null || page <= 0) {
            return 1L;
        }

        return Long.valueOf(page);
    }

    public static Long getSizeOrDefault(QueryDTO.PageInfo pageInfo) {
        Integer size = pageInfo.getSize();

        if (size == null || size <= 0) {
            return 15L;
        }

        if (size > 300) {
            return 300L;
        }

        return Long.valueOf(size);
    }

}
