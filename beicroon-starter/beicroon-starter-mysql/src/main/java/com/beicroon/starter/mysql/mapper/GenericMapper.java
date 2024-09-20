package com.beicroon.starter.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.mysql.helper.RemoveHelper;
import com.beicroon.starter.mysql.model.GenericModel;
import com.beicroon.starter.mysql.utils.QueryUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public interface GenericMapper<T extends GenericModel> extends BaseMapper<T> {

    default T first(SFunction<T, ?> column, Object value) {
        if (value == null) {
            return null;
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().eq(column, value);

        return this.first(wrapper);
    }

    default <R> R first(SFunction<T, ?> column, Object value, SFunction<T, R> select) {
        if (value == null) {
            return null;
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().select(select).eq(column, value);

        T first = this.first(wrapper);

        if (first == null) {
            return null;
        }

        return select.apply(first);
    }

    default T first(QueryWrapper<T> wrapper) {
        wrapper.last("LIMIT 1");

        return this.selectOne(wrapper);
    }

    default <R> R first(QueryWrapper<T> wrapper, SFunction<T, R> select) {
        wrapper.lambda().select(select);

        T first = this.first(wrapper);

        if (first == null) {
            return null;
        }

        return select.apply(first);
    }

    default T first(LambdaQueryWrapper<T> wrapper) {
        wrapper.last("LIMIT 1");

        return this.selectOne(wrapper);
    }

    default <R> R first(LambdaQueryWrapper<T> wrapper, SFunction<T, R> select) {
        wrapper.select(select);

        T first = this.first(wrapper);

        if (first == null) {
            return null;
        }

        return select.apply(first);
    }

    default T firstOrError(SFunction<T, ?> column, Object value, String message) {
        if (value == null) {
            throw ExceptionUtils.business(message);
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().eq(column, value);

        return this.firstOrError(wrapper, message);
    }

    default T firstOrError(QueryWrapper<T> wrapper, String message) {
        T t = this.first(wrapper);

        if (t == null) {
            throw ExceptionUtils.business(message);
        }

        return t;
    }

    default T firstOrError(LambdaQueryWrapper<T> wrapper, String message) {
        T t = this.first(wrapper);

        if (t == null) {
            throw ExceptionUtils.business(message);
        }

        return t;
    }

    default T getById(Serializable id) {
        if (id == null) {
            return null;
        }

        if (id instanceof Long && ((long) id) <= 0) {
            return null;
        }

        return this.selectById(id);
    }

    default T getByIdOrError(Serializable id, String message) {
        T t = this.getById(id);

        if (t == null) {
            throw ExceptionUtils.business(message);
        }

        return t;
    }

    default List<T> list(SFunction<T, ?> column, Object value) {
        if (value == null) {
            return EmptyUtils.emptyList();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().eq(column, value);

        return this.list(wrapper);
    }

    default List<T> list(SFunction<T, ?> column, Collection<?> values) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptyList();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().in(column, values);

        return this.list(wrapper);
    }

    default <R> Set<R> list(SFunction<T, ?> column, Object value, SFunction<T, R> mapper) {
        if (value == null) {
            return EmptyUtils.emptySet();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().select(mapper).eq(column, value);

        return ListUtils.toSet(this.list(wrapper), mapper);
    }

    default <R> Set<R> list(SFunction<T, ?> column, Collection<?> values, SFunction<T, R> mapper) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptySet();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().select(mapper).in(column, values);

        return ListUtils.toSet(this.list(wrapper), mapper);
    }

    default List<T> list(QueryWrapper<T> wrapper) {
        return this.selectList(wrapper);
    }

    default <R> Set<R> list(QueryWrapper<T> wrapper, SFunction<T, R> mapper) {
        wrapper.lambda().select(mapper);

        return ListUtils.toSet(this.list(wrapper), mapper);
    }

    default List<T> list(LambdaQueryWrapper<T> wrapper) {
        return this.selectList(wrapper);
    }

    default <R> Set<R> list(LambdaQueryWrapper<T> wrapper, SFunction<T, R> mapper) {
        wrapper.select(mapper);

        return ListUtils.toSet(this.list(wrapper), mapper);
    }

    default List<T> listOrError(SFunction<T, ?> column, Object value, String message) {
        if (value == null) {
            throw ExceptionUtils.business(message);
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().eq(column, value);

        return this.listOrError(wrapper, message);
    }

    default List<T> listOrError(SFunction<T, ?> column, Collection<?> values, String message) {
        if (EmptyUtils.isEmpty(values)) {
            throw ExceptionUtils.business(message);
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().in(column, values);

        return this.listOrError(wrapper, message);
    }

    default List<T> listOrError(QueryWrapper<T> wrapper, String message) {
        List<T> data = this.list(wrapper);

        if (EmptyUtils.isEmpty(data)) {
            throw ExceptionUtils.business(message);
        }

        return data;
    }

    default List<T> listOrError(LambdaQueryWrapper<T> wrapper, String message) {
        List<T> data = this.list(wrapper);

        if (EmptyUtils.isEmpty(data)) {
            throw ExceptionUtils.business(message);
        }

        return data;
    }

    default List<T> listEnable() {
        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper);
    }

    default <R> Set<R> listEnable(SFunction<T, R> mapper) {
        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().select(mapper);
        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return ListUtils.toSet(this.list(wrapper), mapper);
    }

    default List<T> listByIds(Collection<? extends Serializable> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptyList();
        }

        return this.selectBatchIds(ids);
    }

    default List<T> listByIdsAndEnable(Collection<Long> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptyList();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);
        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper);
    }

    default <R> Set<R> listByIdsAndEnable(Collection<Long> ids, SFunction<T, R> mapper) {
        if (EmptyUtils.isEmpty(ids)) {
            return EmptyUtils.emptySet();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().select(mapper);
        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);
        wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);

        return this.list(wrapper, mapper);
    }

    default List<T> listByIdsOrError(Collection<Long> ids, String message) {
        List<T> data = this.listByIds(ids);

        if (EmptyUtils.isEmpty(data)) {
            throw ExceptionUtils.business(message);
        }

        return data;
    }

    default void chunkById(Consumer<List<T>> consumer) {
        this.chunkById(10000L, QueryUtils.newQueryWrapper(), consumer);
    }

    default void chunkById(int size, Consumer<List<T>> consumer) {
        this.chunkById(size, QueryUtils.newQueryWrapper(), consumer);
    }

    default void chunkById(QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.chunkById(10000L, wrapper, consumer);
    }

    default void chunkById(Function<QueryWrapper<T>, List<T>> getter, Consumer<List<T>> consumer) {
        this.chunkById(getter, 10000L, QueryUtils.newQueryWrapper(), consumer);
    }

    default void chunkById(long size, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.chunkById(this::list, size, wrapper, consumer);
    }

    default void chunkById(Function<QueryWrapper<T>, List<T>> getter, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.chunkById(getter, 10000L, wrapper, consumer);
    }

    default void chunkById(String field, Function<QueryWrapper<T>, List<T>> getter, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.chunkById(field, getter, 10000L, wrapper, consumer);
    }

    default void chunkById(Function<QueryWrapper<T>, List<T>> getter, long size, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.chunkById(SystemConstant.PRIMARY_KEY_NAME, getter, size, wrapper, consumer);
    }

    default void chunkById(String field, Function<QueryWrapper<T>, List<T>> getter, long size, QueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        long id = 0L;

        while (true) {
            QueryWrapper<T> query = wrapper.clone();

            query.gt(field, id);
            query.last(String.format("LIMIT %d", size));

            List<T> data = getter.apply(query);

            if (EmptyUtils.isEmpty(data)) {
                break;
            }

            consumer.accept(data);

            id = data.get(data.size() - 1).getId();
        }
    }

    default boolean remove(SFunction<T, ?> column, Object value) {
        if (value == null) {
            return false;
        }

        UpdateWrapper<T> wrapper = Wrappers.update();

        RemoveHelper.setRemoveWrapper(wrapper);

        wrapper.lambda().eq(column, value);

        return SqlHelper.retBool(this.update(wrapper));
    }

    default boolean remove(SFunction<T, ?> column, Collection<?> values) {
        if (EmptyUtils.isEmpty(values)) {
            return false;
        }

        UpdateWrapper<T> wrapper = Wrappers.update();

        RemoveHelper.setRemoveWrapper(wrapper);

        wrapper.lambda().in(column, values);

        return SqlHelper.retBool(this.update(wrapper));
    }

    default boolean removeById(Serializable id) {
        if (id == null) {
            return false;
        }

        UpdateWrapper<T> wrapper = Wrappers.update();

        wrapper.eq(SystemConstant.PRIMARY_KEY_NAME, id);

        return this.remove(wrapper);
    }

    default boolean removeByIds(Collection<?> ids) {
        if (EmptyUtils.isEmpty(ids)) {
            return false;
        }

        UpdateWrapper<T> wrapper = Wrappers.update();

        wrapper.in(SystemConstant.PRIMARY_KEY_NAME, ids);

        return this.remove(wrapper);
    }

    default boolean removeByMap(Map<String, Object> columnMap) {
        UpdateWrapper<T> wrapper = Wrappers.update();

        wrapper.allEq(columnMap);

        return this.remove(wrapper);
    }

    default boolean remove(UpdateWrapper<T> wrapper) {
        RemoveHelper.setRemoveWrapper(wrapper);

        return SqlHelper.retBool(this.update(wrapper));
    }

    default boolean existed(SFunction<T, ?> column, Object value) {
        return this.existed(column, value, null);
    }

    default boolean existed(SFunction<T, ?> column, Object value, Long exceptId) {
        if (value == null) {
            return false;
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().eq(column, value);

        if (EmptyUtils.isId(exceptId)) {
            wrapper.ne(SystemConstant.PRIMARY_KEY_NAME, exceptId);
        }

        return this.existed(wrapper);
    }

    default boolean existed(QueryWrapper<T> wrapper) {
        wrapper.last("LIMIT 1");

        return this.selectCount(wrapper) > 0;
    }

    default boolean existed(LambdaQueryWrapper<T> wrapper) {
        wrapper.last("LIMIT 1");

        return this.selectCount(wrapper) > 0;
    }

    default long count(SFunction<T, ?> column, Object value) {
        if (value == null) {
            return EmptyUtils.emptyLong();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().eq(column, value);

        return this.selectCount(wrapper);
    }

    default long count(SFunction<T, ?> column, Collection<?> values) {
        if (EmptyUtils.isEmpty(values)) {
            return EmptyUtils.emptyLong();
        }

        QueryWrapper<T> wrapper = QueryUtils.newQueryWrapper();

        wrapper.lambda().in(column, values);

        return this.selectCount(wrapper);
    }

    default QueryWrapper<T> newQuery() {
        return QueryUtils.newQueryWrapper();
    }

    default LambdaQueryWrapper<T> newLambdaQuery() {
        return QueryUtils.newLambdaWrapper();
    }

}
