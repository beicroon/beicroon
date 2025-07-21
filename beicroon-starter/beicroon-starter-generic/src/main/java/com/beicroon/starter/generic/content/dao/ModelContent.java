package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class ModelContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.model;
            
            import io.swagger.v3.oas.annotations.media.Schema;
            import com.baomidou.mybatisplus.annotation.TableField;
            import com.baomidou.mybatisplus.annotation.TableName;
            import com.beicroon.construct.database.model.GenericModel;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{importContent}}
            @Data
            @EqualsAndHashCode(callSuper = true)
            @TableName(value = "`{{tableName}}`")
            public class {{className}}Model extends GenericModel {
            {{fieldContent}}
            }
            """;

    private ModelContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{importContent}}", table.getImportString())
                .replace("{{tableName}}", table.getTableName())
                .replace("{{className}}", table.getClassName())
                .replace("{{fieldContent}}", table.getModelContent())
                ;
    }
    
}
