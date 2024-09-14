package com.beicroon.starter.mysql.repository.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.mysql.mapper.GenericMapper;
import com.beicroon.starter.mysql.model.GenericModel;
import com.beicroon.starter.mysql.repository.ISuperRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class SuperRepository<M extends GenericMapper<T>, T extends GenericModel> extends ServiceImpl<M, T> implements ISuperRepository<T> {

    public T first(SFunction<T, ?> column, Object value) {
        return this.getBaseMapper().first(column, value);
    }

    public <R> R first(SFunction<T, ?> column, Object value, SFunction<T, R> select) {
        return this.getBaseMapper().first(column, value, select);
    }

    public T first(QueryWrapper<T> wrapper) {
        return this.getBaseMapper().first(wrapper);
    }

    public <R> R first(QueryWrapper<T> wrapper, SFunction<T, R> select) {
        return this.getBaseMapper().first(wrapper, select);
    }

    public T first(LambdaQueryWrapper<T> wrapper) {
        return this.getBaseMapper().first(wrapper);
    }

    public <R> R first(LambdaQueryWrapper<T> wrapper, SFunction<T, R> select) {
        return this.getBaseMapper().first(wrapper, select);
    }

    public T firstOrError(SFunction<T, ?> column, Object value, String message) {
        return this.getBaseMapper().firstOrError(column, value, message);
    }

    public T firstOrError(QueryWrapper<T> wrapper, String message) {
        return this.getBaseMapper().firstOrError(wrapper, message);
    }

    public T firstOrError(LambdaQueryWrapper<T> wrapper, String message) {
        return this.getBaseMapper().firstOrError(wrapper, message);
    }

    @Override
    public T getById(Serializable id) {
        return this.getBaseMapper().getById(id);
    }

    public T getByIdOrError(Long id, String message) {
        return this.getBaseMapper().getByIdOrError(id, message);
    }

    public List<T> list(SFunction<T, ?> column, Object value) {
        return this.getBaseMapper().list(column, value);
    }

    public List<T> list(SFunction<T, ?> column, Collection<?> values) {
        return this.getBaseMapper().list(column, values);
    }

    public <R> Set<R> list(SFunction<T, ?> column, Object value, SFunction<T, R> mapper) {
        return this.getBaseMapper().list(column, value, mapper);
    }

    public <R> Set<R> list(SFunction<T, ?> column, Collection<?> values, SFunction<T, R> mapper) {
        return this.getBaseMapper().list(column, values, mapper);
    }

    public <R> Set<R> list(QueryWrapper<T> wrapper, SFunction<T, R> mapper) {
        return this.getBaseMapper().list(wrapper, mapper);
    }

    public <R> Set<R> list(LambdaQueryWrapper<T> wrapper, SFunction<T, R> mapper) {
        return this.getBaseMapper().list(wrapper, mapper);
    }

    public List<T> listOrError(SFunction<T, ?> column, Object value, String message) {
        return this.getBaseMapper().listOrError(column, value, message);
    }

    public List<T> listOrError(SFunction<T, ?> column, Collection<?> values, String message) {
        return this.getBaseMapper().listOrError(column, values, message);
    }

    public List<T> listOrError(QueryWrapper<T> wrapper, String message) {
        return this.getBaseMapper().listOrError(wrapper, message);
    }

    public List<T> listOrError(LambdaQueryWrapper<T> wrapper, String message) {
        return this.getBaseMapper().listOrError(wrapper, message);
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> ids) {
        return this.getBaseMapper().listByIds(ids);
    }

    public List<T> listByIdsOrError(Collection<Long> ids, String message) {
        return this.getBaseMapper().listByIdsOrError(ids, message);
    }

    public void chunkById(Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(consumer);
    }

    public void chunkById(int size, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(size, consumer);
    }

    public void chunkById(QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(wrapper, consumer);
    }

    public void chunkById(Function<QueryWrapper<T>, List<T>> getter, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(getter, consumer);
    }

    public void chunkById(long size, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(size, wrapper, consumer);
    }

    public void chunkById(Function<QueryWrapper<T>, List<T>> getter, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(getter, wrapper, consumer);
    }

    public void chunkById(String field, Function<QueryWrapper<T>, List<T>> getter, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(field, getter, wrapper, consumer);
    }

    public void chunkById(Function<QueryWrapper<T>, List<T>> getter, long size, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(SystemConstant.PRIMARY_KEY_NAME, getter, size, wrapper, consumer);
    }

    public void chunkById(String field, Function<QueryWrapper<T>, List<T>> getter, long size, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.getBaseMapper().chunkById(field, getter, size, wrapper, consumer);
    }

    public boolean remove(SFunction<T, ?> column, Object value) {
        return this.getBaseMapper().remove(column, value);
    }

    public boolean remove(SFunction<T, ?> column, Collection<?> values) {
        return this.getBaseMapper().remove(column, values);
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return this.getBaseMapper().removeByMap(columnMap);
    }

    @Override
    public boolean removeById(Serializable id) {
        return this.getBaseMapper().removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<?> ids) {
        return this.getBaseMapper().removeByIds(ids);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> ids) {
        return this.getBaseMapper().removeByIds(ids);
    }

    public boolean remove(UpdateWrapper<T> wrapper) {
        return this.getBaseMapper().remove(wrapper);
    }

    @Override
    @Deprecated
    public boolean remove(Wrapper<T> wrapper) {
        throw new UnsupportedOperationException("该方法已弃用");
    }

    @Override
    @Deprecated
    public boolean removeById(T entity) {
        throw new UnsupportedOperationException("该方法已弃用");
    }

    @Override
    @Deprecated
    public boolean removeBatchByIds(Collection<?> list, boolean useFill) {
        throw new UnsupportedOperationException("该方法已弃用");
    }

    @Override
    @Deprecated
    public boolean removeByIds(Collection<?> ids, boolean useFill) {
        throw new UnsupportedOperationException("该方法已弃用");
    }

    @Override
    @Deprecated
    public boolean removeById(Serializable id, boolean useFill) {
        throw new UnsupportedOperationException("该方法已弃用");
    }

    public boolean existed(SFunction<T, ?> column, Object value) {
        return this.getBaseMapper().existed(column, value);
    }

    public boolean existed(SFunction<T, ?> column, Object value, Long exceptId) {
        return this.getBaseMapper().existed(column, value, exceptId);
    }

    public boolean existed(QueryWrapper<T> wrapper) {
        return this.getBaseMapper().existed(wrapper);
    }

    public boolean existed(LambdaQueryWrapper<T> wrapper) {
        return this.getBaseMapper().existed(wrapper);
    }

    public long count(SFunction<T, ?> column, Object value) {
        return this.getBaseMapper().count(column, value);
    }

    public long count(SFunction<T, ?> column, Collection<?> values) {
        return this.getBaseMapper().count(column, values);
    }

    public QueryWrapper<T> newQuery() {
        return this.getBaseMapper().newQuery();
    }

    public LambdaQueryWrapper<T> newLambdaQuery() {
        return this.getBaseMapper().newLambdaQuery();
    }

}
