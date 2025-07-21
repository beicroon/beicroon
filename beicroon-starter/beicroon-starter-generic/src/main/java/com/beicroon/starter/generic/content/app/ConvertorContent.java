package com.beicroon.starter.generic.content.app;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class ConvertorContent {
    
    private static final String CONTENT = """
            package {{modulePackage}}.dao.convertor;

            import com.beicroon.construct.base.entity.PageInfo;
            import com.beicroon.construct.utils.ListUtils;
            import {{modulePackage}}.dao.model.{{className}}Model;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}CreateDTO;
            import {{modulePackage}}.entity.{{pathName}}.dto.{{className}}UpdateDTO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}BaseVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}DetailVO;
            import {{modulePackage}}.entity.{{pathName}}.vo.{{className}}PageVO;
            import com.beicroon.provider.mybatisplus.helper.ConvertorHelper;
            import org.mapstruct.*;

            import java.util.Collection;
            import java.util.List;
            import java.util.function.BiConsumer;
            import java.util.function.Consumer;

            @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
            public interface {{className}}Convertor {

                @Mapping(target = "id", ignore = true)
                @Mapping(target = "createdAt", ignore = true)
                @Mapping(target = "creatorId", ignore = true)
                @Mapping(target = "creatorCode", ignore = true)
                @Mapping(target = "creatorName", ignore = true)
                @Mapping(target = "modifiedAt", ignore = true)
                @Mapping(target = "modifierId", ignore = true)
                @Mapping(target = "modifierCode", ignore = true)
                @Mapping(target = "modifierName", ignore = true){{mappingContent}}
                {{className}}Model toCreator({{className}}CreateDTO dto);

                @AfterMapping
                default void fillCreator(@MappingTarget {{className}}Model model, {{className}}CreateDTO dto) {
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
                @Mapping(target = "modifierName", ignore = true){{mappingContent}}
                {{className}}Model toUpdater({{className}}Model model, {{className}}UpdateDTO dto);

                @AfterMapping
                default void fillModifier(@MappingTarget {{className}}Model newModel, {{className}}Model model, {{className}}UpdateDTO dto) {
                    ConvertorHelper.fillModifier(newModel);
                }

                {{className}}BaseVO toBaseVO({{className}}Model model);

                default List<{{className}}BaseVO> toBaseVO(Collection<{{className}}Model> models) {
                    return ListUtils.toList(models, this::toBaseVO);
                }

                {{className}}PageVO toPageVO({{className}}Model model);

                default PageInfo<{{className}}PageVO> toPageVO(PageInfo<{{className}}Model> page) {
                    return ConvertorHelper.toPageVO(page, this::toPageVO);
                }

                default PageInfo<{{className}}PageVO> toPageVO(PageInfo<{{className}}Model> page, BiConsumer<{{className}}Model, {{className}}PageVO> consumer) {
                    return ConvertorHelper.toPageVO(page, model -> {
                        {{className}}PageVO vo = this.toPageVO(model);

                        consumer.accept(model, vo);

                        return vo;
                    });
                }

                {{className}}DetailVO toDetailVO({{className}}Model model);

                default {{className}}DetailVO toDetailVO({{className}}Model model, Consumer<{{className}}DetailVO> consumer) {
                    {{className}}DetailVO vo = this.toDetailVO(model);

                    consumer.accept(vo);

                    return vo;
                }

                @AfterMapping
                default void fillVO(@MappingTarget {{className}}BaseVO vo, {{className}}Model model) {

                }

            }
            """;
    
    private ConvertorContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{className}}", table.getClassName())
                .replace("{{mappingContent}}", table.getMappingContent())
                ;
    }
    
}
