package com.beicroon.construct.database.repository;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.database.lambda.SFunction;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.construct.database.wrapper.IUpdateWrapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface GenericRepository<T extends GenericModel> {

    Class<T> getEntityClass();

    IQueryWrapper<T> newQueryWrapper();

    IUpdateWrapper<T> newUpdateWrapper();

    T getById(Long id);

    T getByIdOrError(Long id, String message);

    T first(SFunction<T, ?> field, Object value);

    T first(IQueryWrapper<T> wrapper);

    T firstOrError(SFunction<T, ?> field, Object value, String message);

    List<T> list(IQueryWrapper<T> wrapper);

    List<T> listByIdsAndEnable(Collection<Long> ids);

    List<T> listEnable();

    List<T> list(SFunction<T, ?> field, Object value);

    List<T> list(SFunction<T, ?> field, Collection<?> values);

    <R> Set<R> list(SFunction<T, ?> field, Object value, SFunction<T, R> select);

    <R> Set<R> list(SFunction<T, ?> field, Collection<?> values, SFunction<T, R> select);

    <R> Set<R> listByIdsAndEnable(Collection<Long> ids, SFunction<T, R> select);

    <R> Set<R> list(IQueryWrapper<T> wrapper, SFunction<T, R> select);

    <R> Set<R> listEnable(SFunction<T, R> select);

    List<T> listByIds(Collection<Long> ids);

    PageInfo<T> page(QueryDTO.PageInfo pageInfo, IQueryWrapper<T> wrapper);

    Long count(IQueryWrapper<T> wrapper);

    default void chunkById(Consumer<List<T>> consumer) {
        this.chunkById(this.newQueryWrapper(), 1000, consumer);
    }

    default void chunkById(int size, Consumer<List<T>> consumer) {
        this.chunkById(this.newQueryWrapper(), size, consumer);
    }

    default void chunkById(IQueryWrapper<T> wrapper, Consumer<List<T>> consumer) {
        this.chunkById(this.newQueryWrapper(), 1000, consumer);
    }

    void chunkById(IQueryWrapper<T> wrapper, int size, Consumer<List<T>> consumer);

    boolean insert(T model);

    boolean insert(Collection<T> models);

    boolean update(IUpdateWrapper<T> wrapper);

    boolean updateById(T model);

    boolean existed(IQueryWrapper<T> wrapper);

    boolean existed(SFunction<T, ?> field, Object value);

    boolean existed(SFunction<T, ?> field, Object value, Long exceptId);

    boolean existed(SFunction<T, ?> field, Collection<?> values, Long exceptId);

    boolean remove(SFunction<T, ?> field, Object value);

    boolean remove(SFunction<T, ?> field, Collection<?> values);

    boolean removeById(Long id);

    boolean removeByIds(Collection<Long> ids);

    boolean delete(SFunction<T, ?> field, Object value);

    boolean delete(SFunction<T, ?> field, Collection<?> values);

    boolean deleteById(Long id);

    boolean deleteByIds(Collection<Long> ids);

}
