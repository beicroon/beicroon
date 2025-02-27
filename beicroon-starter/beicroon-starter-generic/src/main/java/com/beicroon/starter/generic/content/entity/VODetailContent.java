package com.beicroon.starter.generic.content.entity;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class VODetailContent {

    private static final String CONTENT = """
            package {{modulePackage}}.entity.{{pathName}}.vo;
            
            import com.beicroon.construct.annotation.ApiModel;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            
            @Data
            @ApiModel(name = "查询{{tableComment}}详情出参")
            @EqualsAndHashCode(callSuper = true)
            public class {{className}}DetailVO extends {{className}}BaseVO {
            
            }
            """;

    private VODetailContent() {
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
