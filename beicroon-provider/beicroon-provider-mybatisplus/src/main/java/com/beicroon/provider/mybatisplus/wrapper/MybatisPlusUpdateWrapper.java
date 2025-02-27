package com.beicroon.provider.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.wrapper.IUpdateWrapper;
import lombok.Getter;

import java.util.Collection;
import java.util.function.Consumer;

@Getter
public class MybatisPlusUpdateWrapper<T extends GenericModel> implements IUpdateWrapper<T> {

    private final Class<T> clazz;

    private final UpdateWrapper<T> wrapper;

    public MybatisPlusUpdateWrapper(Class<T> clazz) {
        this.clazz = clazz;

        this.wrapper = Wrappers.update();
    }

    public MybatisPlusUpdateWrapper(Class<T> clazz, UpdateWrapper<T> wrapper) {
        this.clazz = clazz;

        this.wrapper = wrapper;
    }

    @Override
    public IUpdateWrapper<T> set(String field, Object value) {
        this.wrapper.set(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> eq(String field, Object value) {
        this.wrapper.eq(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> neq(String field, Object value) {
        this.wrapper.ne(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> lt(String field, Object value) {
        this.wrapper.lt(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> lte(String field, Object value) {
        this.wrapper.le(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> gt(String field, Object value) {
        this.wrapper.gt(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> gte(String field, Object value) {
        this.wrapper.ge(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> in(String field, Collection<?> values) {
        this.wrapper.in(field, values);

        return this;
    }

    @Override
    public IUpdateWrapper<T> in(String field, Object... values) {
        this.wrapper.in(field, values);

        return this;
    }

    @Override
    public IUpdateWrapper<T> notIn(String field, Collection<?> values) {
        this.wrapper.notIn(field, values);

        return this;
    }

    @Override
    public IUpdateWrapper<T> notIn(String field, Object... values) {
        this.wrapper.notIn(field, values);

        return this;
    }

    @Override
    public IUpdateWrapper<T> like(String field, Object value) {
        this.wrapper.like(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> notLike(String field, Object value) {
        this.wrapper.notLike(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> likeLeft(String field, Object value) {
        this.wrapper.likeLeft(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> notLikeLeft(String field, Object value) {
        this.wrapper.notLikeLeft(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> likeRight(String field, Object value) {
        this.wrapper.likeRight(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> notLikeRight(String field, Object value) {
        this.wrapper.notLikeRight(field, value);

        return this;
    }

    @Override
    public IUpdateWrapper<T> between(String field, Object start, Object end) {
        this.wrapper.between(field, start, end);

        return this;
    }

    @Override
    public IUpdateWrapper<T> notBetween(String field, Object start, Object end) {
        this.wrapper.notBetween(field, start, end);

        return this;
    }

    @Override
    public IUpdateWrapper<T> isNull(String field) {
        this.wrapper.isNull(field);

        return this;
    }

    @Override
    public IUpdateWrapper<T> isNotNull(String field) {
        this.wrapper.isNotNull(field);

        return this;
    }

    @Override
    public IUpdateWrapper<T> orderByAsc(String field) {
        this.wrapper.orderByAsc(field);

        return this;
    }

    @Override
    public IUpdateWrapper<T> orderByDesc(String field) {
        this.wrapper.orderByDesc(field);

        return this;
    }

    @Override
    public IUpdateWrapper<T> and(Consumer<IUpdateWrapper<T>> consumer) {
        this.wrapper.and(subWrapper -> consumer.accept(new MybatisPlusUpdateWrapper<>(this.getClazz(), subWrapper)));

        return this;
    }

    @Override
    public IUpdateWrapper<T> or(Consumer<IUpdateWrapper<T>> consumer) {
        this.wrapper.or(subWrapper -> consumer.accept(new MybatisPlusUpdateWrapper<>(this.getClazz(), subWrapper)));

        return this;
    }

}
