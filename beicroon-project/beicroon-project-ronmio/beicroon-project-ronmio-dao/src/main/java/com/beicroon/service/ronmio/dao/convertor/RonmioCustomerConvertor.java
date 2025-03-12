package com.beicroon.service.ronmio.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.ronmio.dao.model.RonmioCustomerModel;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerCreateDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.dto.RonmioCustomerUpdateDTO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerBaseVO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerDetailVO;
import com.beicroon.service.ronmio.entity.ronmiocustomer.vo.RonmioCustomerPageVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RonmioCustomerConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    RonmioCustomerModel toCreator(RonmioCustomerCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RonmioCustomerModel model, RonmioCustomerCreateDTO dto) {
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
    RonmioCustomerModel toUpdater(RonmioCustomerUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget RonmioCustomerModel model, RonmioCustomerUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    RonmioCustomerBaseVO toBaseVO(RonmioCustomerModel model);

    default List<RonmioCustomerBaseVO> toBaseVO(Collection<RonmioCustomerModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    RonmioCustomerPageVO toPageVO(RonmioCustomerModel model);

    default PageInfo<RonmioCustomerPageVO> toPageVO(PageInfo<RonmioCustomerModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<RonmioCustomerPageVO> toPageVO(PageInfo<RonmioCustomerModel> page, BiConsumer<RonmioCustomerModel, RonmioCustomerPageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            RonmioCustomerPageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    RonmioCustomerDetailVO toDetailVO(RonmioCustomerModel model);

    default RonmioCustomerDetailVO toDetailVO(RonmioCustomerModel model, Consumer<RonmioCustomerDetailVO> consumer) {
        RonmioCustomerDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget RonmioCustomerBaseVO vo, RonmioCustomerModel model) {

    }

}
