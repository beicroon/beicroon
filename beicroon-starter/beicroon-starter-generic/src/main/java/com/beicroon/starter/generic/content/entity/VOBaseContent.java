package com.beicroon.starter.generic.content.entity;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class VOBaseContent {

    private static final String CONTENT = """
            package {{modulePackage}}.entity.{{pathName}}.vo;

            import io.swagger.v3.oas.annotations.media.Schema;
            import com.beicroon.construct.base.entity.GenericVO;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{importContent}}
            @Data
            @EqualsAndHashCode(callSuper = true)
            public class {{className}}BaseVO extends GenericVO {
            {{fieldContent}}
            }
            """;

    private VOBaseContent() {
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
