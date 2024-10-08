package com.beicroon.starter.generic.content.convertor;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class RelationConvertorContent {

    private static final String CONTENT = """
            package {{package}}.convertor;

            import com.beicroon.construct.utils.List;
            import {{package}}.model.{{filename}}Model;
            import {{modulePackage}}.dto.{{filename}}CreateDTO;
            import {{modulePackage}}.vo.{{filename}}BaseVO;
            import com.beicroon.starter.dao.helper.ConvertorHelper;
            import org.mapstruct.*;

            import java.util.List;

            @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
            public interface {{filename}}Convertor {

                @Mapping(target = "id", ignore = true)
                @Mapping(target = "createdAt", ignore = true)
                @Mapping(target = "creatorId", ignore = true)
                @Mapping(target = "creatorCode", ignore = true)
                @Mapping(target = "creatorName", ignore = true)
                @Mapping(target = "modifiedAt", ignore = true)
                @Mapping(target = "modifierId", ignore = true)
                @Mapping(target = "modifierCode", ignore = true)
                @Mapping(target = "modifierName", ignore = true)
                {{filename}}Model toEntity({{filename}}CreateDTO dto);

                @AfterMapping
                default void fillCreator(@MappingTarget {{filename}}Model model, {{filename}}CreateDTO dto) {
                    ConvertorHelper.fillCreator(model);
                }

                {{filename}}BaseVO toBaseVO({{filename}}Model model);

                default List<{{filename}}BaseVO> toBaseVO(List<{{filename}}Model> models) {
                    return List.toList(models, this::toBaseVO);
                }

                @AfterMapping
                default void fillVO(@MappingTarget {{filename}}BaseVO vo, {{filename}}Model model) {

                }

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getDaoPackage())
                .replace("{{modulePackage}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replace("{{filename}}", table.getFilename());
    }

}
