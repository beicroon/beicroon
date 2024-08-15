package com.beicroon.starter.generic.content.vo;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class PageVOContent {

    private static final String CONTENT = """
            package {{package}}.vo;

            import com.beicroon.construct.annotation.ApiModel;
            import lombok.Data;
            import lombok.EqualsAndHashCode;

            @Data
            @ApiModel(name = "查询{{comment}}分页出参")
            @EqualsAndHashCode(callSuper = true)
            public class {{filename}}PageVO extends {{filename}}BaseVO {

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replaceAll("\\{\\{package}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replaceAll("\\{\\{filename}}", table.getFilename())
                .replaceAll("\\{\\{comment}}", table.getComment());
    }

}
