package com.beicroon.starter.mysql.helper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.construct.entity.QueryDTO;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.starter.mysql.handler.TabHandler;
import com.beicroon.starter.mysql.model.GenericModel;

import java.util.List;

public final class QueryHelper {

    private QueryHelper() {

    }

    public static <Q extends QueryDTO, T extends GenericModel, H extends TabHandler<T>> void fillTabWrapper(Q dto, List<H> handlers, QueryWrapper<T> wrapper) {
        if (EmptyUtils.isNotEmpty(dto.getTabCode())) {
            for (H handler : handlers) {
                if (!handler.getCode().equals(dto.getTabCode())) {
                    continue;
                }

                handler.fillQuery(wrapper);

                if (EmptyUtils.isEmpty(handler.getChildren()) || EmptyUtils.isEmpty(dto.getSubTabCode())) {
                    return;
                }

                for (TabHandler<T> child : handler.getChildren()) {
                    if (!child.getCode().equals(dto.getSubTabCode())) {
                        continue;
                    }

                    child.fillQuery(wrapper);

                    return;
                }

                QueryHelper.noPermission(wrapper);

                return;
            }

            QueryHelper.noPermission(wrapper);
        }
    }

    public static <T extends GenericModel> void noPermission(QueryWrapper<T> wrapper) {
        wrapper.eq("1", EmptyUtils.emptyInteger());
    }

}
