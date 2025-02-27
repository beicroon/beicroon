package com.beicroon.starter.generic.content.entity;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class DTOCreateContent {

    private static final String CONTENT = """
            package {{modulePackage}}.entity.{{pathName}}.dto;
            
            import com.beicroon.construct.annotation.ApiModel;
            import com.beicroon.construct.annotation.ApiModelProperty;
            import com.beicroon.construct.base.entity.GenericDTO;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{importContent}}
            @Data
            @ApiModel(name = "新增{{tableComment}}入参")
            @EqualsAndHashCode(callSuper = true)
            public class {{className}}CreateDTO extends GenericDTO {
            {{fieldContent}}
            }
            """;

    private DTOCreateContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{importContent}}", table.getImportString())
                .replace("{{tableComment}}", table.getTableComment())
                .replace("{{className}}", table.getClassName())
                .replace("{{fieldContent}}", table.getEntityContent())
                ;
    }
    
}
