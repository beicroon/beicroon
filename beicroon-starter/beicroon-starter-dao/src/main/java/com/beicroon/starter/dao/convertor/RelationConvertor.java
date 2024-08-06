package com.beicroon.starter.dao.convertor;

import com.beicroon.construct.entity.CreateDTO;
import com.beicroon.construct.entity.GenericVO;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.starter.mysql.model.GenericModel;
import org.mapstruct.Mapping;

import java.util.List;

public interface RelationConvertor<T extends GenericModel, M extends GenericModel, C extends CreateDTO, B extends GenericVO> extends BaseConvertor<M, C, B> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    M toRelation(T t, C c);

    default List<M> toRelation(T t, List<C> cs) {
        return ListUtils.toList(cs, c -> this.toRelation(t, c));
    }

}
