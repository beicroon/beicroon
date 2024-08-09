package com.beicroon.starter.dao.convertor;


import com.beicroon.construct.entity.CreateDTO;
import com.beicroon.construct.entity.CreateDTOWithDisable;
import com.beicroon.construct.entity.GenericVO;
import com.beicroon.construct.enums.BooleanEnums;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import com.beicroon.starter.dao.helper.DisableHelper;
import com.beicroon.starter.mysql.model.DisableModel;
import com.beicroon.starter.mysql.model.GenericModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseConvertor<M extends GenericModel, C extends CreateDTO, B extends GenericVO> {

    B toBaseVO(M m);

    default List<B> toBaseVO(List<M> ms) {
        return ListUtils.toList(ms, this::toBaseVO);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
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

    @AfterMapping
    default void fillDisable(@MappingTarget DisableModel m, CreateDTOWithDisable c) {
        if (BooleanEnums.isTrue(c.getDisabledFlag())) {
            DisableHelper.disable(m);
        }
    }

}
