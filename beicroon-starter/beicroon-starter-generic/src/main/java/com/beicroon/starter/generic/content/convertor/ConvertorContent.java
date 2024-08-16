package com.beicroon.starter.generic.content.convertor;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class ConvertorContent {

    private static final String CONTENT = """
            package {{package}}.convertor;

            import {{package}}.model.{{filename}}Model;
            import {{modulePackage}}.dto.{{filename}}CreateDTO;
            import {{modulePackage}}.dto.{{filename}}UpdateDTO;
            import {{modulePackage}}.vo.{{filename}}BaseVO;
            import {{modulePackage}}.vo.{{filename}}DetailVO;
            import {{modulePackage}}.vo.{{filename}}PageVO;
            import com.beicroon.starter.dao.convertor.GenericConvertor;
            import org.mapstruct.Mapper;
            import org.mapstruct.ReportingPolicy;

            @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
            public interface {{filename}}Convertor extends GenericConvertor<{{filename}}Model, {{filename}}CreateDTO, {{filename}}UpdateDTO, {{filename}}BaseVO, {{filename}}DetailVO, {{filename}}PageVO> {

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getDaoPackage())
                .replace("{{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replace("{{filename}}", table.getFilename());
    }

}
