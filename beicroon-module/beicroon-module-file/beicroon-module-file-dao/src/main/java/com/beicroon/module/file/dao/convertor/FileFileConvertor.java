package com.beicroon.module.file.dao.convertor;

import com.beicroon.construct.base.entity.PageInfo;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.module.file.dao.bo.FileInfoBO;
import com.beicroon.module.file.dao.model.FileFileModel;
import com.beicroon.module.file.entity.filefile.dto.FileFileCreateDTO;
import com.beicroon.module.file.entity.filefile.dto.FileFileUpdateDTO;
import com.beicroon.module.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.module.file.entity.filefile.vo.FileFileDetailVO;
import com.beicroon.module.file.entity.filefile.vo.FileFilePageVO;
import com.beicroon.provider.mybatisplus.helper.ConvertorHelper;
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
    @Mapping(target = "fileUrl", source = "url")
    @Mapping(target = "fileType", source = "fileInfo.fileType")
    @Mapping(target = "fileName", source = "fileInfo.fileName")
    @Mapping(target = "fileExt", source = "fileInfo.fileExt")
    @Mapping(target = "fileSize", source = "fileInfo.fileSize")
    FileFileModel toCreator(String url, FileInfoBO fileInfo);

    @AfterMapping
    default void fillCreator(@MappingTarget FileFileModel model, FileInfoBO fileInfo) {
        ConvertorHelper.fillCreator(model);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "creatorId", ignore = true)
    @Mapping(target = "creatorCode", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifierId", ignore = true)
    @Mapping(target = "modifierCode", ignore = true)
    @Mapping(target = "fileUrl", source = "dto.fileUrl")
    @Mapping(target = "fileType", source = "dto.fileType")
    @Mapping(target = "fileName", source = "dto.fileName")
    @Mapping(target = "fileExt", source = "dto.fileExt")
    @Mapping(target = "fileSize", source = "dto.fileSize")
    FileFileModel toCreator(FileFileCreateDTO dto);

    @AfterMapping
    default void fillCreator(@MappingTarget FileFileModel model, FileFileCreateDTO dto) {
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
    @Mapping(target = "fileUrl", source = "dto.fileUrl")
    @Mapping(target = "fileType", source = "dto.fileType")
    @Mapping(target = "fileName", source = "dto.fileName")
    @Mapping(target = "fileExt", source = "dto.fileExt")
    @Mapping(target = "fileSize", source = "dto.fileSize")
    FileFileModel toUpdater(FileFileModel model, FileFileUpdateDTO dto);

    @AfterMapping
    default void fillModifier(@MappingTarget FileFileModel newModel, FileFileModel model, FileFileUpdateDTO dto) {
        ConvertorHelper.fillModifier(newModel);
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
