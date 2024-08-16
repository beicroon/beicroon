package com.beicroon.starter.generic.content.vo;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class DetailVOContent {

    private static final String CONTENT = """
            package {{package}}.vo;

            import com.beicroon.construct.annotation.ApiModel;
            import lombok.Data;
            import lombok.EqualsAndHashCode;

            @Data
            @ApiModel(name = "查询{{comment}}详情出参")
            @EqualsAndHashCode(callSuper = true)
            public class {{filename}}DetailVO extends {{filename}}BaseVO {

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replace("{{filename}}", table.getFilename())
                .replace("{{comment}}", table.getComment());
    }

}
