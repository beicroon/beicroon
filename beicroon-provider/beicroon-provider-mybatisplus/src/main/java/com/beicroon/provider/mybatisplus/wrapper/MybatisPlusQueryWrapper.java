package com.beicroon.provider.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import lombok.Getter;

import java.util.Collection;
import java.util.function.Consumer;

@Getter
public class MybatisPlusQueryWrapper<T extends GenericModel> implements IQueryWrapper<T> {

    private final Class<T> clazz;

    private final QueryWrapper<T> wrapper;

    public MybatisPlusQueryWrapper(Class<T> clazz) {
        this.clazz = clazz;

        this.wrapper = Wrappers.query(clazz);
    }

    public MybatisPlusQueryWrapper(Class<T> clazz, QueryWrapper<T> wrapper) {
        this.clazz = clazz;

        this.wrapper = wrapper;
    }

    @Override
    public IQueryWrapper<T> select(String field) {
        this.wrapper.select(field);

        return this;
    }

    @Override
    public IQueryWrapper<T> eq(String field, Object value) {
        this.wrapper.eq(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> neq(String field, Object value) {
        this.wrapper.ne(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> lt(String field, Object value) {
        this.wrapper.lt(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> lte(String field, Object value) {
        this.wrapper.le(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> gt(String field, Object value) {
        this.wrapper.gt(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> gte(String field, Object value) {
        this.wrapper.ge(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> in(String field, Collection<?> values) {
        this.wrapper.in(field, values);

        return this;
    }

    @Override
    public IQueryWrapper<T> in(String field, Object... values) {
        this.wrapper.in(field, values);

        return this;
    }

    @Override
    public IQueryWrapper<T> notIn(String field, Collection<?> values) {
        this.wrapper.notIn(field, values);

        return this;
    }

    @Override
    public IQueryWrapper<T> notIn(String field, Object... values) {
        this.wrapper.notIn(field, values);

        return this;
    }

    @Override
    public IQueryWrapper<T> like(String field, Object value) {
        this.wrapper.like(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> notLike(String field, Object value) {
        this.wrapper.notLike(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> likeLeft(String field, Object value) {
        this.wrapper.likeLeft(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> notLikeLeft(String field, Object value) {
        this.wrapper.notLikeLeft(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> likeRight(String field, Object value) {
        this.wrapper.likeRight(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> notLikeRight(String field, Object value) {
        this.wrapper.notLikeRight(field, value);

        return this;
    }

    @Override
    public IQueryWrapper<T> between(String field, Object start, Object end) {
        this.wrapper.between(field, start, end);

        return this;
    }

    @Override
    public IQueryWrapper<T> notBetween(String field, Object start, Object end) {
        this.wrapper.notBetween(field, start, end);

        return this;
    }

    @Override
    public IQueryWrapper<T> isNull(String field) {
        this.wrapper.isNull(field);

        return this;
    }

    @Override
    public IQueryWrapper<T> isNotNull(String field) {
        this.wrapper.isNotNull(field);

        return this;
    }

    @Override
    public IQueryWrapper<T> orderByAsc(String field) {
        this.wrapper.orderByAsc(field);

        return this;
    }

    @Override
    public IQueryWrapper<T> orderByDesc(String field) {
        this.wrapper.orderByDesc(field);

        return this;
    }

    @Override
    public IQueryWrapper<T> and(Consumer<IQueryWrapper<T>> consumer) {
        this.wrapper.and(subWrapper -> consumer.accept(new MybatisPlusQueryWrapper<>(this.getClazz(), subWrapper)));

        return this;
    }

    @Override
    public IQueryWrapper<T> or() {
        this.wrapper.or();

        return this;
    }

    @Override
    public IQueryWrapper<T> or(Consumer<IQueryWrapper<T>> consumer) {
        this.wrapper.or(subWrapper -> consumer.accept(new MybatisPlusQueryWrapper<>(this.getClazz(), subWrapper)));

        return this;
    }

    @Override
    public IQueryWrapper<T> cloneWrapper() {
        return new MybatisPlusQueryWrapper<>(this.getClazz(), this.getWrapper().clone());
    }

}
