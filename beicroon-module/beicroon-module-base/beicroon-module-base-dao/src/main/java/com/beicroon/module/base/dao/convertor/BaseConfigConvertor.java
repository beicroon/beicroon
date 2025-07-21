package com.beicroon.module.base.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.module.base.dao.model.BaseConfigModel;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigCreateDTO;
import com.beicroon.module.base.entity.baseconfig.dto.BaseConfigUpdateDTO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigBaseVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigDetailVO;
import com.beicroon.module.base.entity.baseconfig.vo.BaseConfigPageVO;
import com.beicroon.provider.mybatisplus.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseConfigConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    @Mapping(target = "configKey", source = "dto.configKey")
    @Mapping(target = "configValue", source = "dto.configValue")
    @Mapping(target = "configRemark", source = "dto.configRemark")
    @Mapping(target = "componentName", source = "dto.componentName")
    @Mapping(target = "componentWidth", source = "dto.componentWidth")
    @Mapping(target = "componentRequired", source = "dto.componentRequired")
    @Mapping(target = "componentMultiple", source = "dto.componentMultiple")
    BaseConfigModel toCreator(BaseConfigCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget BaseConfigModel model, BaseConfigCreateDTO dto) {
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
    @Mapping(target = "configValue", source = "dto.configValue")
    @Mapping(target = "configRemark", source = "dto.configRemark")
    @Mapping(target = "componentName", source = "dto.componentName")
    @Mapping(target = "componentWidth", source = "dto.componentWidth")
    @Mapping(target = "componentRequired", source = "dto.componentRequired")
    @Mapping(target = "componentMultiple", source = "dto.componentMultiple")
    BaseConfigModel toUpdater(BaseConfigModel model, BaseConfigUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget BaseConfigModel newModel, BaseConfigModel model, BaseConfigUpdateDTO dto) {
        ConvertorHelper.fillModifier(newModel);
    }

    BaseConfigBaseVO toBaseVO(BaseConfigModel model);

    default List<BaseConfigBaseVO> toBaseVO(Collection<BaseConfigModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    BaseConfigPageVO toPageVO(BaseConfigModel model);

    default PageInfo<BaseConfigPageVO> toPageVO(PageInfo<BaseConfigModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<BaseConfigPageVO> toPageVO(PageInfo<BaseConfigModel> page, BiConsumer<BaseConfigModel, BaseConfigPageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            BaseConfigPageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    BaseConfigDetailVO toDetailVO(BaseConfigModel model);

    default BaseConfigDetailVO toDetailVO(BaseConfigModel model, Consumer<BaseConfigDetailVO> consumer) {
        BaseConfigDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget BaseConfigBaseVO vo, BaseConfigModel model) {

    }

}
