package com.beicroon.construct.database.wrapper;

import com.beicroon.construct.database.content.TableContent;
import com.beicroon.construct.database.lambda.SFunction;
import com.beicroon.construct.database.model.GenericModel;

import java.util.Collection;
import java.util.function.Consumer;

public interface IQueryWrapper<T extends GenericModel> {

    Object getWrapper();

    Class<T> getClazz();

    IQueryWrapper<T> select(String field);

    IQueryWrapper<T> eq(String field, Object value);

    IQueryWrapper<T> neq(String field, Object value);

    IQueryWrapper<T> lt(String field, Object value);

    IQueryWrapper<T> lte(String field, Object value);

    IQueryWrapper<T> gt(String field, Object value);

    IQueryWrapper<T> gte(String field, Object value);

    IQueryWrapper<T> in(String field, Collection<?> values);

    IQueryWrapper<T> in(String field, Object... values);

    IQueryWrapper<T> notIn(String field, Collection<?> values);

    IQueryWrapper<T> notIn(String field, Object... values);

    IQueryWrapper<T> like(String field, Object value);

    IQueryWrapper<T> notLike(String field, Object value);

    IQueryWrapper<T> likeLeft(String field, Object value);

    IQueryWrapper<T> notLikeLeft(String field, Object value);

    IQueryWrapper<T> likeRight(String field, Object value);

    IQueryWrapper<T> notLikeRight(String field, Object value);

    IQueryWrapper<T> between(String field, Object start, Object end);

    IQueryWrapper<T> notBetween(String field, Object start, Object end);

    IQueryWrapper<T> isNull(String field);

    IQueryWrapper<T> isNotNull(String field);

    IQueryWrapper<T> orderByAsc(String field);

    IQueryWrapper<T> orderByDesc(String field);

    IQueryWrapper<T> and(Consumer<IQueryWrapper<T>> consumer);

    IQueryWrapper<T> or();

    IQueryWrapper<T> or(Consumer<IQueryWrapper<T>> consumer);

    IQueryWrapper<T> cloneWrapper();

    default IQueryWrapper<T> select(SFunction<T, ?> field) {
        return this.select(TableContent.getTableField(this.getClazz(), field));
    }

    default IQueryWrapper<T> eq(SFunction<T, ?> field, Object value) {
        return this.eq(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> neq(SFunction<T, ?> field, Object value) {
        return this.neq(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> lt(SFunction<T, ?> field, Object value) {
        return this.lt(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> lte(SFunction<T, ?> field, Object value) {
        return this.lte(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> gt(SFunction<T, ?> field, Object value) {
        return this.gt(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> gte(SFunction<T, ?> field, Object value) {
        return this.gte(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> in(SFunction<T, ?> field, Collection<?> values) {
        return this.in(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IQueryWrapper<T> in(SFunction<T, ?> field, Object... values) {
        return this.in(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IQueryWrapper<T> notIn(SFunction<T, ?> field, Collection<?> values) {
        return this.notIn(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IQueryWrapper<T> notIn(SFunction<T, ?> field, Object... values) {
        return this.notIn(TableContent.getTableField(this.getClazz(), field), values);
    }

    default IQueryWrapper<T> like(SFunction<T, ?> field, Object value) {
        return this.like(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> notLike(SFunction<T, ?> field, Object value) {
        return this.notLike(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> likeLeft(SFunction<T, ?> field, Object value) {
        return this.likeLeft(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> notLikeLeft(SFunction<T, ?> field, Object value) {
        return this.notLikeLeft(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> likeRight(SFunction<T, ?> field, Object value) {
        return this.likeRight(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> notLikeRight(SFunction<T, ?> field, Object value) {
        return this.notLikeRight(TableContent.getTableField(this.getClazz(), field), value);
    }

    default IQueryWrapper<T> between(SFunction<T, ?> field, Object start, Object end) {
        return this.between(TableContent.getTableField(this.getClazz(), field), start, end);
    }

    default IQueryWrapper<T> notBetween(SFunction<T, ?> field, Object start, Object end) {
        return this.notBetween(TableContent.getTableField(this.getClazz(), field), start, end);
    }

    default IQueryWrapper<T> isNull(SFunction<T, ?> field) {
        return this.isNull(TableContent.getTableField(this.getClazz(), field));
    }

    default IQueryWrapper<T> isNotNull(SFunction<T, ?> field) {
        return this.isNotNull(TableContent.getTableField(this.getClazz(), field));
    }

    default IQueryWrapper<T> orderByAsc(SFunction<T, ?> field) {
        return this.orderByAsc(TableContent.getTableField(this.getClazz(), field));
    }

    default IQueryWrapper<T> orderByDesc(SFunction<T, ?> field) {
        return this.orderByDesc(TableContent.getTableField(this.getClazz(), field));
    }

}
