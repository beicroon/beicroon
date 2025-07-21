package com.beicroon.provider.mybatisplus.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.annotation.KeywordsSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.utils.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class WrapperUtils {

    private WrapperUtils() {

    }

    public static <T extends GenericModel, Q extends QueryDTO> QueryWrapper<T> fillQueryWrapper(Q dto, QueryWrapper<T> wrapper) {
        // 模糊查询
        if (EmptyUtils.isNotEmpty(dto.getKeywords())) {
            KeywordsSearch keywordsSearch = dto.getClass().getAnnotation(KeywordsSearch.class);

            if (keywordsSearch != null) {
                List<String> values = Arrays.asList(keywordsSearch.value());

                wrapper.and(sw -> ListUtils.foreach(values, s -> sw.or().like(s, dto.getKeywords())));
            }
        }

        // 字段查询
        ClassUtils.forAllField(dto, field -> {
            FieldSearch fieldSearch = field.getAnnotation(FieldSearch.class);

            if (fieldSearch == null) {
                return;
            }

            field.setAccessible(true);

            Object value;

            try {
                value = field.get(dto);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }

            // 有值才查询
            if (!EmptyUtils.hasValue(value)) {
                return;
            }

            wrapper.and(sw -> WrapperUtils.buildSql(sw, fieldSearch, value));
        });

        // 禁用查询
        if (EmptyUtils.isNotEmpty(dto.getDisabledFlag())) {
            if (BooleanEnums.isTrue(dto.getDisabledFlag())) {
                wrapper.isNotNull(SystemConstant.DISABLE_KEY_NAME);
            } else if (BooleanEnums.isFalse(dto.getDisabledFlag())) {
                wrapper.isNull(SystemConstant.DISABLE_KEY_NAME);
            }
        }

        return wrapper;
    }

    public static <T extends GenericModel> void buildSql(QueryWrapper<T> wrapper, FieldSearch fieldSearch, Object value) {
        // ne 查询
        if (fieldSearch.ne()) {
            wrapper.ne(fieldSearch.value(), value);
        }
        // in 查询
        else if (fieldSearch.in()) {
            if (!(value instanceof Collection)) {
                throw new RuntimeException("in查询值必须是一个集合");
            }

            wrapper.in(fieldSearch.value(), (Collection<?>) value);
        }
        // like 查询
        else if (fieldSearch.like()) {
            wrapper.like(fieldSearch.value(), value);
        }
        // likeLeft 查询
        else if (fieldSearch.likeLeft()) {
            wrapper.likeLeft(fieldSearch.value(), value);
        }
        // likeRight 查询
        else if (fieldSearch.likeRight()) {
            wrapper.likeRight(fieldSearch.value(), value);
        }
        // gt 查询
        else if (fieldSearch.gt()) {
            wrapper.gt(fieldSearch.value(), value);
        }
        // ge 查询
        else if (fieldSearch.ge()) {
            wrapper.ge(fieldSearch.value(), value);
        }
        // lt 查询
        else if (fieldSearch.lt()) {
            wrapper.lt(fieldSearch.value(), value);
        }
        // le 查询
        else if (fieldSearch.le()) {
            wrapper.le(fieldSearch.value(), value);
        }
        // between 查询
        else if (fieldSearch.between()) {
            List<String> values = StringUtils.split((String) value, "~", 2);

            if (EmptyUtils.isEmpty(values) || values.size() < 2) {
                throw new RuntimeException(String.format("请使用'~'分隔[%s]字段", fieldSearch.value()));
            }

            String start = values.get(0);

            String end = values.get(1);

            if (EmptyUtils.isEmpty(start) && EmptyUtils.isEmpty(end)) {
                throw new RuntimeException(String.format("范围查询[%s]字段不能为空", fieldSearch.value()));
            }

            if (EmptyUtils.isNotEmpty(start) && EmptyUtils.isEmpty(end)) {
                wrapper.ge(fieldSearch.value(), start);
            } else if (EmptyUtils.isEmpty(start) && EmptyUtils.isNotEmpty(end)) {
                wrapper.le(fieldSearch.value(), end);
            } else {
                wrapper.between(fieldSearch.value(), start, end);
            }
        }
        // = 查询
        else {
            wrapper.eq(fieldSearch.value(), value);
        }
    }

    public static <T extends GenericModel> void between(LambdaQueryWrapper<T> query, SFunction<T, ?> column, LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null) {
            query.between(column, start, end);
        } else if (start != null) {
            query.ge(column, start);
        } else if (end != null) {
            query.le(column, end);
        }
    }

    public static <T extends GenericModel> void effective(LambdaQueryWrapper<T> query, SFunction<T, ?> effectAt, SFunction<T, ?> expiredAt) {
        query.and(subQuery -> {
            LocalDateTime now = TimeUtils.now();

            subQuery.or(sub -> {
                sub.le(effectAt, now);
                sub.isNull(expiredAt);
            });

            subQuery.or(sub -> {
                sub.ge(expiredAt, now);
                sub.isNull(effectAt);
            });

            subQuery.or(sub -> {
                sub.le(effectAt, now);
                sub.ge(expiredAt, now);
            });

            subQuery.or(sub -> {
                sub.isNull(effectAt);
                sub.isNull(expiredAt);
            });
        });
    }

}
