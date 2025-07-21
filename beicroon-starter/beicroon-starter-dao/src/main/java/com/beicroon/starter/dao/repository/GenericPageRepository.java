package com.beicroon.starter.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.base.entity.TabVO;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.provider.mybatisplus.helper.ConvertorHelper;
import com.beicroon.provider.mybatisplus.utils.WrapperUtils;
import com.beicroon.starter.dao.handler.TabHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface GenericPageRepository<Q extends QueryDTO, T extends GenericModel> extends GenericRepository<T> {

    List<? extends TabHandler<T>> getTabHandlers();

    void fillQueryWrapper(Q dto, QueryWrapper<T> wrapper);

    default QueryWrapper<T> newQueryWrapper(Q dto) {
        QueryWrapper<T> wrapper = this.newQueryWrapper();

        this.fillQueryWrapper(dto, WrapperUtils.fillQueryWrapper(dto, wrapper));

        if (EmptyUtils.isNotEmpty(dto.getTabCode()) && EmptyUtils.isNotEmpty(this.getTabHandlers())) {
            for (TabHandler<T> tabHandler : this.getTabHandlers()) {
                if (tabHandler.count() && tabHandler.getCode().equals(dto.getTabCode())) {
                    tabHandler.fillQuery(wrapper.lambda());

                    break;
                }
            }
        }

        return wrapper;
    }

    default LambdaQueryWrapper<T> newLambdaQueryWrapper(Q dto) {
        return this.newQueryWrapper(dto).lambda();
    }

    default List<T> list(Q dto) {
        if (EmptyUtils.isId(dto.getId())) {
            return ListUtils.of(this.getById(dto.getId()));
        }

        return this.list(this.newQueryWrapper(dto));
    }

    default PageInfo<T> page(Q dto) {
        return this.page(dto, SystemConstant.PRIMARY_KEY_NAME, false);
    }

    default PageInfo<T> page(Q dto, String orderBy, boolean isAsc) {
        if (EmptyUtils.isId(dto.getId())) {
            PageInfo<T> pageInfo = new PageInfo<>(dto.getPageInfo());

            pageInfo.getData().add(this.getById(dto.getId()));

            return pageInfo;
        }

        QueryWrapper<T> wrapper = this.newQueryWrapper(dto);

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

    default List<TabVO> tab(QueryWrapper<T> wrapper, List<? extends TabHandler<T>> handlers) {
        if (EmptyUtils.isEmpty(handlers)) {
            return EmptyUtils.emptyList();
        }

        List<TabVO> vos = new ArrayList<>(handlers.size());

        for (TabHandler<T> handler : handlers) {
            if (!handler.count()) {
                vos.add(new TabVO(handler.getSort(), handler.getCode(), handler.getName(), handler.hasChildren().getCode()));

                continue;
            }

            QueryWrapper<T> query = wrapper.clone();

            handler.fillQuery(query.lambda());

            vos.add(new TabVO(handler.getSort(), handler.getCode(), handler.getName(), this.count(query), handler.hasChildren().getCode()));
        }

        vos.sort(Comparator.comparing(TabVO::getSort));

        return vos;
    }

    default boolean disableById(Long id) {
        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return disable(wrapper);
    }

    default boolean disableByIds(Collection<Long> ids) {
        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return disable(wrapper);
    }

    default boolean disable(UpdateWrapper<T> wrapper) {
        ConvertorHelper.fillDisabler(wrapper);

        return this.update(wrapper);
    }

    default boolean enableById(Long id) {
        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return this.enable(wrapper);
    }

    default boolean enableByIds(Collection<Long> ids) {
        UpdateWrapper<T> wrapper = this.newUpdateWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return this.enable(wrapper);
    }

    default boolean enable(UpdateWrapper<T> wrapper) {
        wrapper.set(SystemConstant.DISABLE_KEY_NAME, null);
        wrapper.set("`disabled_id`", EmptyUtils.emptyLong());
        wrapper.set("`disabled_code`", EmptyUtils.emptyString());
        wrapper.set("`disabled_name`", EmptyUtils.emptyString());

        return this.update(wrapper);
    }

}
