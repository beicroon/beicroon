package com.beicroon.starter.generic.content.entity;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class VOPageContent {

    private static final String CONTENT = """
            package {{modulePackage}}.entity.{{pathName}}.vo;

            import io.swagger.v3.oas.annotations.media.Schema;
            import lombok.Data;
            import lombok.EqualsAndHashCode;

            @Data
            @EqualsAndHashCode(callSuper = true)
            public class {{className}}PageVO extends {{className}}BaseVO {

            }
            """;

    private VOPageContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{pathName}}", table.getPathName())
                .replace("{{tableComment}}", table.getTableComment())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
