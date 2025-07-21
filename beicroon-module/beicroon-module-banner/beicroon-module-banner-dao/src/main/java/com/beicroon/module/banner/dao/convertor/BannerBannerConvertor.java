package com.beicroon.module.banner.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.module.banner.dao.model.BannerBannerModel;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerCreateDTO;
import com.beicroon.module.banner.entity.bannerbanner.dto.BannerBannerUpdateDTO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerBaseVO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerDetailVO;
import com.beicroon.module.banner.entity.bannerbanner.vo.BannerBannerPageVO;
import com.beicroon.provider.mybatisplus.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BannerBannerConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    @Mapping(target = "bannerUrl", source = "dto.bannerUrl")
    @Mapping(target = "bannerRemark", source = "dto.bannerRemark")
    @Mapping(target = "bannerSorting", source = "dto.bannerSorting")
    @Mapping(target = "bannerEffectAt", source = "dto.bannerEffectAt")
    @Mapping(target = "bannerExpiredAt", source = "dto.bannerExpiredAt")
    @Mapping(target = "redirectUrl", source = "dto.redirectUrl")
    BannerBannerModel toCreator(BannerBannerCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget BannerBannerModel model, BannerBannerCreateDTO dto) {
        ConvertorHelper.fillCreator(model);
    }

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    @Mapping(target = "bannerUrl", source = "dto.bannerUrl")
    @Mapping(target = "bannerRemark", source = "dto.bannerRemark")
    @Mapping(target = "bannerSorting", source = "dto.bannerSorting")
    @Mapping(target = "bannerEffectAt", source = "dto.bannerEffectAt")
    @Mapping(target = "bannerExpiredAt", source = "dto.bannerExpiredAt")
    @Mapping(target = "redirectUrl", source = "dto.redirectUrl")
    BannerBannerModel toUpdater(BannerBannerModel model, BannerBannerUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget BannerBannerModel newModel, BannerBannerModel model, BannerBannerUpdateDTO dto) {
        ConvertorHelper.fillModifier(newModel);
    }

    BannerBannerBaseVO toBaseVO(BannerBannerModel model);

    default List<BannerBannerBaseVO> toBaseVO(Collection<BannerBannerModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    BannerBannerPageVO toPageVO(BannerBannerModel model);

    default PageInfo<BannerBannerPageVO> toPageVO(PageInfo<BannerBannerModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<BannerBannerPageVO> toPageVO(PageInfo<BannerBannerModel> page, BiConsumer<BannerBannerModel, BannerBannerPageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            BannerBannerPageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    BannerBannerDetailVO toDetailVO(BannerBannerModel model);

    default BannerBannerDetailVO toDetailVO(BannerBannerModel model, Consumer<BannerBannerDetailVO> consumer) {
        BannerBannerDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget BannerBannerBaseVO vo, BannerBannerModel model) {

    }

}
