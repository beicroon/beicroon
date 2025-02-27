package com.beicroon.construct.database.wrapper;

import com.beicroon.construct.database.content.TableContent;
import com.beicroon.construct.database.lambda.SFunction;
import com.beicroon.construct.database.model.GenericModel;

import java.util.Collection;
import java.util.function.Consumer;

public interface IUpdateWrapper<T extends GenericModel> {

    Object getWrapper();

    Class<T> getClazz();

    IUpdateWrapper<T> set(String field, Object value);

    IUpdateWrapper<T> eq(String field, Object value);

    IUpdateWrapper<T> neq(String field, Object value);

    IUpdateWrapper<T> lt(String field, Object value);

    IUpdateWrapper<T> lte(String field, Object value);

    IUpdateWrapper<T> gt(String field, Object value);

    IUpdateWrapper<T> gte(String field, Object value);

    IUpdateWrapper<T> in(String field, Collection<?> values);

    IUpdateWrapper<T> in(String field, Object... values);

    IUpdateWrapper<T> notIn(String field, Collection<?> values);

    IUpdateWrapper<T> notIn(String field, Object... values);

    IUpdateWrapper<T> like(String field, Object value);

    IUpdateWrapper<T> notLike(String field, Object value);

    IUpdateWrapper<T> likeLeft(String field, Object value);

    IUpdateWrapper<T> notLikeLeft(String field, Object value);

    IUpdateWrapper<T> likeRight(String field, Object value);

    IUpdateWrapper<T> notLikeRight(String field, Object value);

    IUpdateWrapper<T> between(String field, Object start, Object end);

    IUpdateWrapper<T> notBetween(String field, Object start, Object end);

    IUpdateWrapper<T> isNull(String field);

    IUpdateWrapper<T> isNotNull(String field);

    IUpdateWrapper<T> orderByAsc(String field);

    IUpdateWrapper<T> orderByDesc(String field);

    IUpdateWrapper<T> and(Consumer<IUpdateWrapper<T>> consumer);

    IUpdateWrapper<T> or(Consumer<IUpdateWrapper<T>> consumer);

    default IUpdateWrapper<T> set(SFunction<T, ?> field, Object value) {
        return this.set(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> eq(SFunction<T, ?> field, Object value) {
        return this.eq(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> neq(SFunction<T, ?> field, Object value) {
        return this.neq(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> lt(SFunction<T, ?> field, Object value) {
        return this.lt(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> lte(SFunction<T, ?> field, Object value) {
        return this.lte(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> gt(SFunction<T, ?> field, Object value) {
        return this.gt(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> gte(SFunction<T, ?> field, Object value) {
        return this.gte(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> in(SFunction<T, ?> field, Collection<?> values) {
        return this.in(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IUpdateWrapper<T> in(SFunction<T, ?> field, Object... values) {
        return this.in(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IUpdateWrapper<T> notIn(SFunction<T, ?> field, Collection<?> values) {
        return this.notIn(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IUpdateWrapper<T> notIn(SFunction<T, ?> field, Object... values) {
        return this.notIn(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IUpdateWrapper<T> like(SFunction<T, ?> field, Object value) {
        return this.like(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> notLike(SFunction<T, ?> field, Object value) {
        return this.notLike(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> likeLeft(SFunction<T, ?> field, Object value) {
        return this.likeLeft(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> notLikeLeft(SFunction<T, ?> field, Object value) {
        return this.notLikeLeft(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> likeRight(SFunction<T, ?> field, Object value) {
        return this.likeRight(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> notLikeRight(SFunction<T, ?> field, Object value) {
        return this.notLikeRight(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IUpdateWrapper<T> between(SFunction<T, ?> field, Object start, Object end) {
        return this.between(TableContent.getTableField(this.getClazz(), field), start, end);
    }

    default IUpdateWrapper<T> notBetween(SFunction<T, ?> field, Object start, Object end) {
        return this.notBetween(TableContent.getTableField(this.getClazz(), field), start, end);
    }

    default IUpdateWrapper<T> isNull(SFunction<T, ?> field) {
        return this.isNull(TableContent.getTableField(this.getClazz(), field));
    }

    default IUpdateWrapper<T> isNotNull(SFunction<T, ?> field) {
        return this.isNotNull(TableContent.getTableField(this.getClazz(), field));
    }

    default IUpdateWrapper<T> orderByAsc(SFunction<T, ?> field) {
        return this.orderByAsc(TableContent.getTableField(this.getClazz(), field));
    }

    default IUpdateWrapper<T> orderByDesc(SFunction<T, ?> field) {
        return this.orderByDesc(TableContent.getTableField(this.getClazz(), field));
    }

}
