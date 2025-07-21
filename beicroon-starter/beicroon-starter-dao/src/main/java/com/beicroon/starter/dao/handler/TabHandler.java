package com.beicroon.starter.dao.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.beicroon.construct.database.model.GenericModel;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.utils.EmptyUtils;

import java.util.List;

public interface TabHandler<T extends GenericModel> {

    default boolean count() {
        return true;
    }

    default boolean isCurrentTab(String code) {
        return EmptyUtils.isNotEmpty(code) && code.equals(this.getCode());
    }

    default Integer getSort() {
        return 0;
    }

    String getCode();

    String getName();

    void fillQuery(LambdaQueryWrapper<T> wrapper);

    default BooleanEnums hasChildren() {
        return BooleanEnums.boolOf(EmptyUtils.isNotEmpty(this.getChildren()));
    }

    default List<? extends TabHandler<T>> getChildren() {
        return EmptyUtils.emptyList();
    }

}
