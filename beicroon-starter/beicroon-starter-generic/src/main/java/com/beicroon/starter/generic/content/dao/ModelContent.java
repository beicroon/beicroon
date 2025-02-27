package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class ModelContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.model;
            
            import com.beicroon.construct.annotation.ApiModelProperty;
            import com.beicroon.construct.annotation.TableField;
            import com.beicroon.construct.annotation.TableName;
            import com.beicroon.construct.database.model.GenericModel;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{importContent}}
            @Data
            @EqualsAndHashCode(callSuper = true)
            @TableName("`{{tableName}}`")
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
