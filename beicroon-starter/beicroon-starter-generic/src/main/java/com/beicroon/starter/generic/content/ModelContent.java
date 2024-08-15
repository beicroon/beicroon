package com.beicroon.starter.generic.content;

import com.beicroon.starter.generic.maker.ApiMysqlMaker;
import com.beicroon.starter.generic.manager.PackageManager;

public class ModelContent {

    private static final String CONTENT = """
            package {{basePackage}}.model;

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
            public class {{fileName}}Model extends GenericModel {
                {{content}}
            }
            """;

    public static String getContent(PackageManager packageManager, ApiMysqlMaker.Table table) {
        return CONTENT
                .replaceAll("\\{\\{basePackage}}", packageManager.getDaoPackage())
                .replaceAll("\\{\\{tableName}}", table.getName())
                .replaceAll("\\{\\{fileName}}", table.getFileName())
                .replaceAll("\\{\\{import}}", table.getImportString())
                .replaceAll("\\{\\{comment}}", table.getComment())
                .replaceAll("\\{\\{content}}", table.getContent());
    }

}
