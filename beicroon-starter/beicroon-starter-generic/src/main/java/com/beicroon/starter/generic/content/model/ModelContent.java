package com.beicroon.starter.generic.content.model;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class ModelContent {

    private static final String CONTENT = """
            package {{package}}.model;

            import com.baomidou.mybatisplus.annotation.TableField;
            import com.baomidou.mybatisplus.annotation.TableName;
            import com.beicroon.construct.annotation.ApiModel;
            import com.beicroon.construct.annotation.ApiModelProperty;
            import com.beicroon.starter.mysql.model.GenericModel;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{import}}
            @Data
            @ApiModel(name = "{{comment}}")
            @TableName("`{{tableName}}`")
            @EqualsAndHashCode(callSuper = true)
            public class {{filename}}Model extends GenericModel {
                {{content}}
            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getDaoPackage())
                .replace("{{tableName}}", table.getTableName())
                .replace("{{filename}}", table.getFilename())
                .replace("{{import}}", table.getImportString())
                .replace("{{comment}}", table.getComment())
                .replace("{{content}}", table.getModelContent());
    }

}
