package com.beicroon.starter.database.helper;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.base.entity.AuthUser;
import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.utils.ClassUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public final class ConvertorHelper {

    private ConvertorHelper() {

    }

    public static <T, R> PageInfo<R> toPageVO(PageInfo<T> page, Function<T, R> function) {
        PageInfo<R> pageInfo = new PageInfo<>();

        pageInfo.setData(ListUtils.toList(page.getData(), function));
        pageInfo.setPage(page.getPage());
        pageInfo.setSize(page.getSize());
        pageInfo.setTotal(page.getTotal());

        return pageInfo;
    }

    public static <T extends GenericModel> void fillCreator(T t) {
        AuthUser authUser = AuthUtils.getUser();

        t.setCreatedAt(LocalDateTime.now());
        t.setCreatorId(authUser.getId());
        t.setCreatorCode(authUser.getCode());
        t.setCreatorName(authUser.getName());
        t.setModifiedAt(t.getCreatedAt());
        t.setModifierId(authUser.getId());
        t.setModifierCode(authUser.getCode());
        t.setModifierName(authUser.getName());
        t.setId(IdWorker.getId(t));
    }

    public static <T extends GenericModel> void fillModifier(T t) {
        AuthUser authUser = AuthUtils.getUser();

        t.setModifiedAt(LocalDateTime.now());
        t.setModifierId(authUser.getId());
        t.setModifierCode(authUser.getCode());
        t.setModifierName(authUser.getName());
    }

    public static <T extends GenericModel> void cover(T from, T to) {
        ConvertorHelper.cover(from, to, EmptyUtils.emptySet());
    }

    public static <T extends GenericModel> void cover(T from, T to, String... ignores) {
        ConvertorHelper.cover(from, to, new HashSet<>(Arrays.asList(ignores)));
    }

    public static <T extends GenericModel> void cover(T from, T to, Set<String> ignores) {
        ClassUtils.forAllField(from, field -> {
            field.setAccessible(true);

            if (ignores.contains(field.getName())) {
                return;
            }

            if (SystemConstant.IGNORE_COLUMNS.contains(field.getName())) {
                return;
            }

            try {
                Object value = field.get(from);

                if (EmptyUtils.hasValue(value)) {
                    field.set(to, value);
                }
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
