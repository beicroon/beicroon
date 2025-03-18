package com.beicroon.project.ronmio.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceContainerModel;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceModel;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoicePriceModel;
import com.beicroon.project.ronmio.dao.model.RonmioPackingListInvoiceRecordModel;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceCreateDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.dto.RonmioPackingListInvoiceUpdateDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceBaseVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoiceDetailVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoice.vo.RonmioPackingListInvoicePageVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.dto.RonmioPackingListInvoiceContainerAppendDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerDetailVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicecontainer.vo.RonmioPackingListInvoiceContainerSearchVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoiceprice.dto.RonmioPackingListInvoicePriceUpgradeDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoiceprice.vo.RonmioPackingListInvoicePriceBaseVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.dto.RonmioPackingListInvoiceRecordCreateDTO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.vo.RonmioPackingListInvoiceRecordBaseVO;
import com.beicroon.project.ronmio.entity.ronmiopackinglistinvoicerecord.vo.RonmioPackingListInvoiceRecordDetailVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RonmioPackingListInvoiceConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    RonmioPackingListInvoiceModel toCreator(RonmioPackingListInvoiceCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RonmioPackingListInvoiceModel model, RonmioPackingListInvoiceCreateDTO dto) {
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
    RonmioPackingListInvoiceModel toUpdater(RonmioPackingListInvoiceUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget RonmioPackingListInvoiceModel model, RonmioPackingListInvoiceUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    RonmioPackingListInvoiceBaseVO toBaseVO(RonmioPackingListInvoiceModel model);

    default List<RonmioPackingListInvoiceBaseVO> toBaseVO(Collection<RonmioPackingListInvoiceModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    RonmioPackingListInvoicePageVO toPageVO(RonmioPackingListInvoiceModel model);

    default PageInfo<RonmioPackingListInvoicePageVO> toPageVO(PageInfo<RonmioPackingListInvoiceModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<RonmioPackingListInvoicePageVO> toPageVO(PageInfo<RonmioPackingListInvoiceModel> page, BiConsumer<RonmioPackingListInvoiceModel, RonmioPackingListInvoicePageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            RonmioPackingListInvoicePageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    RonmioPackingListInvoiceDetailVO toDetailVO(RonmioPackingListInvoiceModel model);

    default RonmioPackingListInvoiceDetailVO toDetailVO(RonmioPackingListInvoiceModel model, Consumer<RonmioPackingListInvoiceDetailVO> consumer) {
        RonmioPackingListInvoiceDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;

    }

    @AfterMapping
    default void fillVO(@MappingTarget RonmioPackingListInvoiceBaseVO vo, RonmioPackingListInvoiceModel model) {

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
    @Mapping(target = "packingListInvoiceId", source = "model.id")
    @Mapping(target = "packingListInvoiceCode", source = "model.code")
    @Mapping(target = "code", source = "dto.code")
    @Mapping(target = "netWeight", source = "dto.netWeight")
    @Mapping(target = "grossWeight", source = "dto.grossWeight")
    RonmioPackingListInvoiceContainerModel toContainerCreator(RonmioPackingListInvoiceModel model, RonmioPackingListInvoiceContainerAppendDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RonmioPackingListInvoiceContainerModel container) {
        ConvertorHelper.fillCreator(container);
    }

    RonmioPackingListInvoiceContainerSearchVO toContainerSearchVO(RonmioPackingListInvoiceContainerModel model);

    RonmioPackingListInvoiceContainerDetailVO toContainerDetailVO(RonmioPackingListInvoiceContainerModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    @Mapping(target = "packingListInvoiceId", source = "model.id")
    @Mapping(target = "packingListInvoiceCode", source = "model.code")
    @Mapping(target = "containerId", source = "container.id")
    @Mapping(target = "containerCode", source = "container.code")
    @Mapping(target = "receiveName", ignore = true)
    @Mapping(target = "level", source = "dto.level")
    @Mapping(target = "thickness", source = "dto.thickness")
    @Mapping(target = "width", source = "dto.width")
    @Mapping(target = "length", source = "dto.length")
    @Mapping(target = "commodityCode", ignore = true)
    @Mapping(target = "commodityName", ignore = true)
    @Mapping(target = "count", source = "dto.count")
    @Mapping(target = "volume", source = "dto.volume")
    @Mapping(target = "netWeight", source = "dto.netWeight")
    @Mapping(target = "grossWeight", source = "dto.grossWeight")
    @Mapping(target = "unitPrice", source = "dto.unitPrice")
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "destination", ignore = true)
    @Mapping(target = "remark", ignore = true)
    RonmioPackingListInvoiceRecordModel toRecordCreator(RonmioPackingListInvoiceModel model, RonmioPackingListInvoiceContainerModel container, RonmioPackingListInvoiceRecordCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RonmioPackingListInvoiceRecordModel model) {
        ConvertorHelper.fillCreator(model);
    }

    default List<RonmioPackingListInvoiceRecordModel> toRecordCreator(RonmioPackingListInvoiceModel model, RonmioPackingListInvoiceContainerModel container, List<RonmioPackingListInvoiceRecordCreateDTO> dtoList) {
        return ListUtils.toList(dtoList, dto -> {
            RonmioPackingListInvoiceRecordModel creator = this.toRecordCreator(model, container, dto);

            container.setCount(NumberUtils.add(container.getCount(), creator.getCount()));
            container.setVolume(NumberUtils.add(container.getVolume(), creator.getVolume()));
            container.setNetWeight(NumberUtils.add(container.getNetWeight(), creator.getNetWeight()));
            container.setGrossWeight(NumberUtils.add(container.getGrossWeight(), creator.getGrossWeight()));

            return creator;
        });
    }

    RonmioPackingListInvoiceRecordBaseVO toRecordBaseVO(RonmioPackingListInvoiceRecordModel model);

    default List<RonmioPackingListInvoiceRecordBaseVO> toRecordBaseVO(List<RonmioPackingListInvoiceRecordModel> models) {
        return ListUtils.toList(models, this::toRecordBaseVO);
    }

    @Mapping(target = "count", ignore = true)
    @Mapping(target = "volume", ignore = true)
    @Mapping(target = "netWeight", ignore = true)
    @Mapping(target = "grossWeight", ignore = true)
    @Mapping(target = "specification", ignore = true)
    RonmioPackingListInvoiceRecordDetailVO toRecordDetailVO(RonmioPackingListInvoiceRecordModel model);

    @Mapping(target = "packingListInvoiceId", source = "packingListInvoiceId")
    @Mapping(target = "packingListInvoiceCode", source = "packingListInvoiceCode")
    @Mapping(target = "level", source = "level")
    @Mapping(target = "thickness", source = "thickness")
    @Mapping(target = "width", source = "width")
    @Mapping(target = "minLength", ignore = true)
    @Mapping(target = "maxLength", ignore = true)
    @Mapping(target = "unitPrice", source = "unitPrice")
    RonmioPackingListInvoicePriceBaseVO toPriceBaseVO(RonmioPackingListInvoiceRecordModel model);

    RonmioPackingListInvoicePriceBaseVO toPriceBaseVO(RonmioPackingListInvoicePriceModel model);

    default List<RonmioPackingListInvoicePriceBaseVO> toPriceBaseVO(List<RonmioPackingListInvoicePriceModel> models) {
        return ListUtils.toList(models, this::toPriceBaseVO);
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
    @Mapping(target = "packingListInvoiceId", source = "model.id")
    @Mapping(target = "packingListInvoiceCode", source = "model.code")
    @Mapping(target = "level", source = "dto.level")
    @Mapping(target = "thickness", source = "dto.thickness")
    @Mapping(target = "width", source = "dto.width")
    @Mapping(target = "minLength", source = "dto.minLength")
    @Mapping(target = "maxLength", source = "dto.maxLength")
    @Mapping(target = "unitPrice", source = "dto.unitPrice")
    RonmioPackingListInvoicePriceModel toPriceModel(RonmioPackingListInvoiceModel model, RonmioPackingListInvoicePriceUpgradeDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget RonmioPackingListInvoicePriceModel model) {
        ConvertorHelper.fillCreator(model);
    }

}
