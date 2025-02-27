package com.beicroon.construct.database.repository;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.handler.TabHandler;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.utils.WrapperUtils;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.construct.utils.EmptyUtils;

import java.util.ArrayList;
import java.util.List;

public interface GenericPageRepository<Q extends QueryDTO, T extends GenericModel> extends GenericRepository<T> {

    List<? extends TabHandler<T>> getTabHandlers();

    IQueryWrapper<T> fillQueryWrapper(Q dto, IQueryWrapper<T> wrapper);

    default IQueryWrapper<T> newQueryWrapper(Q dto) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper();

        return this.fillQueryWrapper(dto, WrapperUtils.fillQueryWrapper(dto, wrapper));
    }

    default List<T> list(Q dto) {
        return this.list(this.newQueryWrapper(dto));
    }

    default PageInfo<T> page(Q dto) {
        return this.page(dto, SystemConstant.PRIMARY_KEY_NAME, false);
    }

    default PageInfo<T> page(Q dto, String orderBy, boolean isAsc) {
        IQueryWrapper<T> wrapper = this.newQueryWrapper(dto);

        if (isAsc) {
            wrapper.orderByAsc(orderBy);
        } else {
            wrapper.orderByDesc(orderBy);
        }

        return this.page(dto.getPageInfo(), wrapper);
    }

    default List<TabVO> tab(Q dto) {
        return this.tab(dto, this.getTabHandlers());
    }

    default List<TabVO> tab(Q dto, List<? extends TabHandler<T>> handlers) {
        dto.setTabCode(null);

        return this.tab(this.newQueryWrapper(dto), handlers);
    }

    default List<TabVO> tab(IQueryWrapper<T> wrapper, List<? extends TabHandler<T>> handlers) {
        if (EmptyUtils.isEmpty(handlers)) {
            return EmptyUtils.emptyList();
        }

        List<TabVO> vos = new ArrayList<>(handlers.size());

        for (TabHandler<T> handler : handlers) {
            if (!handler.count()) {
                vos.add(new TabVO(handler.getCode(), handler.getName(), handler.hasChildren().getCode()));

                continue;
            }

            IQueryWrapper<T> query = wrapper.cloneWrapper();

            handler.fillQuery(query);

            vos.add(new TabVO(handler.getCode(), handler.getName(), this.count(query), handler.hasChildren().getCode()));
        }

        return vos;
    }

}
