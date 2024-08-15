package com.beicroon.starter.generic.content.dto;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class CreateDTOContent {

    private static final String CONTENT = """
            package {{package}}.dto;

            import com.beicroon.construct.annotation.ApiModel;
            import com.beicroon.construct.annotation.ApiModelProperty;
            import com.beicroon.construct.entity.CreateDTO;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{import}}
            @Data
            @ApiModel(name = "新增{{comment}}入参")
            @EqualsAndHashCode(callSuper = true)
            public class {{filename}}CreateDTO extends CreateDTO {
                {{content}}
            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replaceAll("\\{\\{package}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replaceAll("\\{\\{filename}}", table.getFilename())
                .replaceAll("\\{\\{import}}", table.getImportString())
                .replaceAll("\\{\\{comment}}", table.getComment())
                .replaceAll("\\{\\{content}}", table.getDtoContent());
    }

}
