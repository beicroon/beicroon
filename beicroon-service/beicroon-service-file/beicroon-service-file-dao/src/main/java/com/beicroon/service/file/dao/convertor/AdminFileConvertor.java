package com.beicroon.service.file.dao.convertor;

import com.beicroon.construct.utils.ListUtils;
import com.beicroon.service.file.dao.model.AdminFileModel;
import com.beicroon.service.file.entity.adminfile.vo.AdminFileBaseVO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminFileConvertor {

    AdminFileBaseVO toBaseVO(AdminFileModel model);

    default List<AdminFileBaseVO> toBaseVO(Collection<AdminFileModel> models) {
        return ListUtils.toList(models, this::toBaseVO);
    }

    @AfterMapping
    default void fillVO(@MappingTarget AdminFileBaseVO vo, AdminFileModel model) {

    }

}
