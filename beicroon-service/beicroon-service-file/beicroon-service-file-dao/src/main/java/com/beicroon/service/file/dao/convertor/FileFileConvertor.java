package com.beicroon.service.file.dao.convertor;

import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.file.dao.model.FileFileModel;
import com.beicroon.service.file.entity.filefile.vo.FileFileBaseVO;
import com.beicroon.starter.database.helper.ConvertorHelper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileFileConvertor {

    default FileFileModel toCreator(MultipartFile file) {
        String filename = file.getOriginalFilename();

        FileFileModel model = new FileFileModel();

        if (EmptyUtils.isNotEmpty(filename)) {
            int index = filename.lastIndexOf(".");

            if (index == -1) {
                model.setName(filename);
            } else {
                model.setName(filename.substring(0, index));
                model.setExt(filename.substring(index + 1));
            }
        }

        model.setSize(file.getSize());

        ConvertorHelper.fillCreator(model);

        return model;
    }

    FileFileBaseVO toBaseVO(FileFileModel model);

    default List<FileFileBaseVO> toBaseVO(Collection<FileFileModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget FileFileBaseVO vo, FileFileModel model) {

    }

}
