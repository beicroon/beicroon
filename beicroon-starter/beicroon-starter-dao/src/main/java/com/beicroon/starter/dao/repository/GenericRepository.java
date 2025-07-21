package com.beicroon.starter.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.database.model.GenericModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface GenericRepository<T extends GenericModel> {

    default QueryWrapper<T> newQueryWrapper() {
        return Wrappers.query();
    }

    default LambdaQueryWrapper<T> newLambdaQueryWrapper() {
        return Wrappers.lambdaQuery();
    }

    default UpdateWrapper<T> newUpdateWrapper() {
        return Wrappers.update();
    }

    T getById(Long id);

    T getByIdOrError(Long id, String message);

    T first(SFunction<T, ?> field, Object value);

    <R> R first(SFunction<T, ?> field, Object value, SFunction<T, R> select);

    T first(QueryWrapper<T> wrapper);

    T first(LambdaQueryWrapper<T> wrapper);

    T selectOne(QueryWrapper<T> wrapper);

    T selectOne(LambdaQueryWrapper<T> wrapper);

    T firstOrError(SFunction<T, ?> field, Object value, String message);

    T firstOrError(QueryWrapper<T> wrapper, String message);

    T firstOrError(LambdaQueryWrapper<T> wrapper, String message);

    List<T> list(QueryWrapper<T> wrapper);

    List<T> list(LambdaQueryWrapper<T> wrapper);

    List<T> listByIdsAndEnable(Collection<Long> ids);

    List<T> listEnable();

    List<T> list(SFunction<T, ?> field, Object value);

    List<T> list(SFunction<T, ?> field, Collection<?> values);

    <R> Set<R> list(SFunction<T, ?> field, Object value, SFunction<T, R> select);

    <R> Set<R> list(SFunction<T, ?> field, Collection<?> values, SFunction<T, R> select);

    <R> Set<R> listByIdsAndEnable(Collection<Long> ids, SFunction<T, R> select);

    <R> Set<R> list(QueryWrapper<T> wrapper, SFunction<T, R> select);

    <R> Set<R> list(LambdaQueryWrapper<T> wrapper, SFunction<T, R> select);

    <R> Set<R> listEnable(SFunction<T, R> select);

    List<T> listByIds(Collection<Long> ids);

    PageInfo<T> page(QueryDTO.PageInfo pageInfo, QueryWrapper<T> wrapper);

    PageInfo<T> page(QueryDTO.PageInfo pageInfo, LambdaQueryWrapper<T> wrapper);

    Long count(SFunction<T, ?> field, Object value);

    Long count(SFunction<T, ?> field, Collection<?> values);

    Long count(QueryWrapper<T> wrapper);

    Long count(LambdaQueryWrapper<T> wrapper);

    default void chunkById(QueryWrapper<T> wrapper, Consumer<Collection<T>> consumer) {
        this.chunkById(wrapper, 1000, consumer);
    }

    void chunkById(QueryWrapper<T> wrapper, int size, Consumer<Collection<T>> consumer);

    boolean insert(T model);

    boolean insert(Collection<T> models);

    boolean update(UpdateWrapper<T> wrapper);

    boolean updateById(T model);

    boolean updateByIds(Collection<T> models);

    boolean existed(QueryWrapper<T> wrapper);

    boolean existed(LambdaQueryWrapper<T> wrapper);

    boolean existed(SFunction<T, ?> field, Object value);

    boolean existed(SFunction<T, ?> field, Object value, Long exceptId);

    boolean existed(SFunction<T, ?> field, Collection<?> values, Long exceptId);

    boolean remove(UpdateWrapper<T> wrapper);

    boolean remove(SFunction<T, ?> field, Object value);

    boolean remove(SFunction<T, ?> field, Collection<?> values);

    boolean removeById(Long id);

    boolean removeByIds(Collection<Long> ids);

    boolean delete(QueryWrapper<T> wrapper);

    boolean delete(LambdaQueryWrapper<T> wrapper);

    boolean delete(SFunction<T, ?> field, Object value);

    boolean delete(SFunction<T, ?> field, Collection<?> values);

    boolean deleteById(Long id);

    boolean deleteByIds(Collection<Long> ids);

}
