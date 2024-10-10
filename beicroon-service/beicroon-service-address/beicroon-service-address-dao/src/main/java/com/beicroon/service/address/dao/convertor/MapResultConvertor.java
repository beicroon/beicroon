package com.beicroon.service.address.dao.convertor;

import com.beicroon.construct.utils.List;
import com.beicroon.service.address.dao.model.MapResultModel;
import com.beicroon.service.address.entity.map.result.dto.MapResultCreateDTO;
import com.beicroon.service.address.entity.map.result.dto.MapResultUpdateDTO;
import com.beicroon.service.address.entity.map.result.vo.MapResultBaseVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultDetailVO;
import com.beicroon.service.address.entity.map.result.vo.MapResultPageVO;
import com.beicroon.starter.dao.helper.ConvertorHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapResultConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    MapResultModel toEntity(MapResultCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget MapResultModel model, MapResultCreateDTO dto) {
        ConvertorHelper.fillCreator(model);
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    MapResultModel toEntity(MapResultUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget MapResultModel model, MapResultUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    MapResultBaseVO toBaseVO(MapResultModel model);

    MapResultPageVO toPageVO(MapResultModel model);

    MapResultDetailVO toDetailVO(MapResultModel model);

    default List<MapResultBaseVO> toBaseVO(List<MapResultModel> models) {
        return List.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget MapResultBaseVO vo, MapResultModel model) {

    }

}
