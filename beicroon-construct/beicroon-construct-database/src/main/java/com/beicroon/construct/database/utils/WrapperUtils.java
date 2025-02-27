package com.beicroon.construct.database.utils;

import com.beicroon.construct.annotation.FieldSearch;
import com.beicroon.construct.annotation.KeywordsSearch;
import com.beicroon.construct.base.entity.QueryDTO;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.database.wrapper.IQueryWrapper;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.utils.ClassUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class WrapperUtils {

    private WrapperUtils() {

    }

    public static <T extends GenericModel, Q extends QueryDTO> IQueryWrapper<T> fillQueryWrapper(Q dto, IQueryWrapper<T> wrapper) {
        // 模糊查询
        if (EmptyUtils.isNotEmpty(dto.getKeywords())) {
            KeywordsSearch keywordsSearch = dto.getClass().getAnnotation(KeywordsSearch.class);

            if (keywordsSearch != null) {
                List<String> values = Arrays.asList(keywordsSearch.name());

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

    public static <T extends GenericModel> void buildSql(IQueryWrapper<T> wrapper, FieldSearch fieldSearch, Object value) {
        // in 查询
        if (fieldSearch.in()) {
            if (!(value instanceof Collection)) {
                throw new RuntimeException("in查询值必须是一个集合");
            }

            wrapper.in(fieldSearch.name(), (Collection<?>) value);
        }
        // like 查询
        else if (fieldSearch.like()) {
            wrapper.like(fieldSearch.name(), value);
        }
        // likeLeft 查询
        else if (fieldSearch.likeLeft()) {
            wrapper.likeLeft(fieldSearch.name(), value);
        }
        // likeRight 查询
        else if (fieldSearch.likeRight()) {
            wrapper.likeRight(fieldSearch.name(), value);
        }
        // gt 查询
        else if (fieldSearch.gt()) {
            wrapper.gt(fieldSearch.name(), value);
        }
        // ge 查询
        else if (fieldSearch.ge()) {
            wrapper.gte(fieldSearch.name(), value);
        }
        // lt 查询
        else if (fieldSearch.lt()) {
            wrapper.lt(fieldSearch.name(), value);
        }
        // le 查询
        else if (fieldSearch.le()) {
            wrapper.lte(fieldSearch.name(), value);
        }
        // between 查询
        else if (fieldSearch.between()) {
            List<String> values = StringUtils.split((String) value, "~", 2);

            if (EmptyUtils.isEmpty(values) || values.size() < 2) {
                throw new RuntimeException("between查询值使用'~'分隔开");
            }

            wrapper.between(fieldSearch.name(), values.get(0), values.get(1));
        }
        // = 查询
        else {
            wrapper.eq(fieldSearch.name(), value);
        }
    }

}
