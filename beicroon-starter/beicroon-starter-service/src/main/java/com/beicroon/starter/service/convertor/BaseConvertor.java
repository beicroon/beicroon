package com.beicroon.starter.service.convertor;


import com.beicroon.construct.entity.CreateDTO;
import com.beicroon.construct.entity.GenericVO;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.mysql.model.GenericModel;
import com.beicroon.starter.service.helper.ConvertorHelper;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseConvertor<M extends GenericModel, C extends CreateDTO, B extends GenericVO> {

    B toBaseVO(M m);

    default List<B> toBaseVO(List<M> ms) {
        return ListUtils.toList(ms, this::toBaseVO);
    }

    M toEntity(C c);

    default void afterToBaseVO(B b, M m) {

    }

    @AfterMapping
    default void fillVO(@MappingTarget B b, M m) {
        this.afterToBaseVO(b, m);
    }

    default void beforeFillCreator(M m) {

    }

    @AfterMapping
    default void fillCreator(@MappingTarget M m, C c) {
        this.beforeFillCreator(m);

        ConvertorHelper.fillCreator(m);
    }

}
