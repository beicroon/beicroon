package com.beicroon.starter.mysql.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.entity.QueryDTO;
import com.beicroon.construct.entity.TabVO;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.mysql.handler.TabHandler;
import com.beicroon.starter.mysql.model.GenericModel;
import com.beicroon.starter.mysql.utils.PageUtils;
import com.beicroon.starter.mysql.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface IGenericRepository<Q extends QueryDTO, T extends GenericModel> extends ISuperRepository<T> {

    void fillQueryWrapper(Q dto, QueryWrapper<T> wrapper);

    default QueryWrapper<T> newQueryWrapper(Q dto) {
        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper(dto);

        this.fillQueryWrapper(dto, wrapper);

        return wrapper;
    }

    default QueryWrapper<T> newPageWrapper(Q dto) {
        return this.newPageWrapper(dto, true);
    }

    default QueryWrapper<T> newPageWrapper(Q dto, boolean order) {
        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper(dto);

        this.fillQueryWrapper(dto, wrapper);

        if (order) {
            wrapper.orderByDesc(SystemConstant.PRIMARY_KEY_NAME);
        }

        return wrapper;
    }

    default List<T> list(Q dto) {
        if (EmptyUtils.isAllEmpty(dto)) {
            return EmptyUtils.emptyList();
        }

        return this.list(this.newQueryWrapper(dto));
    }

    default List<TabVO> tab(Q dto, List<? extends TabHandler<T>> handlers) {
        dto.setTabCode(null);

        return this.tab(handlers, this.newQueryWrapper(dto));
    }

    default List<TabVO> tab(Q dto, List<? extends TabHandler<T>> handlers, Function<QueryWrapper<T>, Long> function) {
        dto.setTabCode(null);

        return this.tab(handlers, this.newQueryWrapper(dto), function);
    }

    default List<TabVO> tab(List<? extends TabHandler<T>> handlers, QueryWrapper<T> wrapper) {
        if (EmptyUtils.isEmpty(handlers)) {
            return EmptyUtils.emptyList();
        }

        List<TabVO> vos = new ArrayList<>(handlers.size());

        for (TabHandler<T> handler : handlers) {
            if (!handler.count()) {
                vos.add(new TabVO(handler.getCode(), handler.getName()));

                continue;
            }

            QueryWrapper<T> query = wrapper.clone();

            handler.fillQuery(query);

            vos.add(new TabVO(handler.getCode(), handler.getName(), this.count(query)));
        }

        return vos;
    }

    default List<TabVO> tab(List<? extends TabHandler<T>> handlers, QueryWrapper<T> wrapper, Function<QueryWrapper<T>, Long> function) {
        if (EmptyUtils.isEmpty(handlers)) {
            return EmptyUtils.emptyList();
        }

        List<TabVO> vos = new ArrayList<>(handlers.size());

        for (TabHandler<T> handler : handlers) {
            if (!handler.count()) {
                vos.add(new TabVO(handler.getCode(), handler.getName()));

                continue;
            }

            QueryWrapper<T> query = wrapper.clone();

            handler.fillQuery(query);

            vos.add(new TabVO(handler.getCode(), handler.getName(), function.apply(query)));
        }

        return vos;
    }

    default List<TabVO> subTab(Q dto, List<? extends TabHandler<T>> handlers) {
        String tabCode = dto.getTabCode();

        dto.setTabCode(null);

        return this.subTab(tabCode, handlers, this.newQueryWrapper(dto));
    }

    default List<TabVO> subTab(String tabCode, List<? extends TabHandler<T>> handlers, QueryWrapper<T> wrapper) {
        if (EmptyUtils.isEmpty(tabCode) || EmptyUtils.isEmpty(handlers)) {
            return EmptyUtils.emptyList();
        }

        TabHandler<T> handler = ListUtils.first(handlers, h -> h.getCode().equals(tabCode));

        if (handler == null) {
            return EmptyUtils.emptyList();
        }

        return this.tab(handler.getChildren(), wrapper);
    }

    default void handleTab(Q dto, List<? extends TabHandler<T>> handlers, QueryWrapper<T> wrapper) {
        if (EmptyUtils.isEmpty(dto.getTabCode())) {
            return;
        }

        TabHandler<T> handler = ListUtils.first(handlers, h -> h.getCode().equals(dto.getTabCode()));

        if (handler == null) {
            wrapper.eq("1", EmptyUtils.emptyInteger());

            return;
        }

        handler.fillQuery(wrapper);

        if (EmptyUtils.isEmpty(dto.getSubTabCode()) || EmptyUtils.isEmpty(handler.getChildren())) {
            return;
        }

        TabHandler<T> subHandler = ListUtils.first(handler.getChildren(), h -> h.getCode().equals(dto.getSubTabCode()));

        if (subHandler == null) {
            wrapper.eq("1", EmptyUtils.emptyInteger());

            return;
        }

        subHandler.fillQuery(wrapper);
    }

    default Page<T> page(Q dto) {
        return this.page(dto, this.newPageWrapper(dto, true));
    }

    default Page<T> page(Q dto, QueryWrapper<T> wrapper) {
        return this.page(PageUtils.build(dto), wrapper);
    }

}
