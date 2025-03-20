package com.beicroon.service.file.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.file.dao.model.FileFileModel;
import com.beicroon.service.file.entity.filefile.dto.FileFileCreateDTO;
import com.beicroon.service.file.entity.filefile.dto.FileFileUpdateDTO;
import com.beicroon.service.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.service.file.entity.filefile.vo.FileFileDetailVO;
import com.beicroon.service.file.entity.filefile.vo.FileFilePageVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileFileConvertor {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "modifierName", ignore = true)
    FileFileModel toCreator(FileFileCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget FileFileModel model, FileFileCreateDTO dto) {
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
    FileFileModel toUpdater(FileFileUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget FileFileModel model, FileFileUpdateDTO dto) {
        ConvertorHelper.fillModifier(model);
    }

    FileFileBaseVO toBaseVO(FileFileModel model);

    default List<FileFileBaseVO> toBaseVO(Collection<FileFileModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    FileFilePageVO toPageVO(FileFileModel model);

    default PageInfo<FileFilePageVO> toPageVO(PageInfo<FileFileModel> page) {
        return ConvertorHelper.toPageVO(page, this::toPageVO);
    }

    default PageInfo<FileFilePageVO> toPageVO(PageInfo<FileFileModel> page, BiConsumer<FileFileModel, FileFilePageVO> consumer) {
        return ConvertorHelper.toPageVO(page, model -> {
            FileFilePageVO vo = this.toPageVO(model);

            consumer.accept(model, vo);

            return vo;
        });
    }

    FileFileDetailVO toDetailVO(FileFileModel model);

    default FileFileDetailVO toDetailVO(FileFileModel model, Consumer<FileFileDetailVO> consumer) {
        FileFileDetailVO vo = this.toDetailVO(model);

        consumer.accept(vo);

        return vo;
    }

    @AfterMapping
    default void fillVO(@MappingTarget FileFileBaseVO vo, FileFileModel model) {

    }

}
