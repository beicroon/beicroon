package com.beicroon.starter.generic.content.dto;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class UpdateDTOContent {

    private static final String CONTENT = """
            package {{package}}.dto;

            import com.beicroon.construct.annotation.ApiModel;
            import com.beicroon.construct.annotation.ApiModelProperty;
            import com.beicroon.construct.entity.UpdateDTO;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{import}}
            @Data
            @ApiModel(name = "修改{{comment}}入参")
            @EqualsAndHashCode(callSuper = true)
            public class {{filename}}UpdateDTO extends UpdateDTO {
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
