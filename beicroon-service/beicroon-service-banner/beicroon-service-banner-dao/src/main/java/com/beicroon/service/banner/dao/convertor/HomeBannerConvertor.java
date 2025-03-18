package com.beicroon.service.banner.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.banner.dao.model.HomeBannerModel;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerCreateDTO;
import com.beicroon.service.banner.entity.homebanner.dto.HomeBannerUpdateDTO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerBaseVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerDetailVO;
import com.beicroon.service.banner.entity.homebanner.vo.HomeBannerPageVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HomeBannerConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    HomeBannerModel toCreator(HomeBannerCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget HomeBannerModel model, HomeBannerCreateDTO dto) {
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
    HomeBannerModel toUpdater(HomeBannerUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget HomeBannerModel model, HomeBannerUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    HomeBannerBaseVO toBaseVO(HomeBannerModel model);

    default List<HomeBannerBaseVO> toBaseVO(Collection<HomeBannerModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    HomeBannerPageVO toPageVO(HomeBannerModel model);

    default PageInfo<HomeBannerPageVO> toPageVO(PageInfo<HomeBannerModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<HomeBannerPageVO> toPageVO(PageInfo<HomeBannerModel> page, BiConsumer<HomeBannerModel, HomeBannerPageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            HomeBannerPageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    HomeBannerDetailVO toDetailVO(HomeBannerModel model);

    default HomeBannerDetailVO toDetailVO(HomeBannerModel model, Consumer<HomeBannerDetailVO> consumer) {
        HomeBannerDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget HomeBannerBaseVO vo, HomeBannerModel model) {

    }

}
