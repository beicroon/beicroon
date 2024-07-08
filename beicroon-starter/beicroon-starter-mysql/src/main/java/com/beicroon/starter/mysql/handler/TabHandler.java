package com.beicroon.starter.mysql.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.starter.mysql.model.GenericModel;

import java.util.List;

public interface TabHandler<T extends GenericModel> {

    default boolean count() {
        return true;
    }

    String getCode();

    String getName();

    void fillQuery(QueryWrapper<T> query);

    default List<? extends TabHandler<T>> getChildren() {
        return EmptyUtils.emptyList();
    }

}
