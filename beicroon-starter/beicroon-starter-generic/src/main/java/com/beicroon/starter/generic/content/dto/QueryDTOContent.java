package com.beicroon.starter.generic.content.dto;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class QueryDTOContent {

    private static final String CONTENT = """
            package {{package}}.dto;

            import com.beicroon.construct.annotation.ApiModel;
            import com.beicroon.construct.annotation.ApiModelProperty;
            import com.beicroon.construct.annotation.FieldSearch;
            import com.beicroon.construct.entity.QueryDTO;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{import}}
            @Data
            @ApiModel(name = "查询{{comment}}入参")
            @EqualsAndHashCode(callSuper = true)
            public class {{filename}}QueryDTO extends QueryDTO {
                {{content}}
            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replaceAll("\\{\\{package}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replaceAll("\\{\\{filename}}", table.getFilename())
                .replaceAll("\\{\\{import}}", table.getImportString())
                .replaceAll("\\{\\{comment}}", table.getComment())
                .replaceAll("\\{\\{content}}", table.getQueryContent());
    }

}
