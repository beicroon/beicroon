package com.beicroon.starter.generic.content.vo;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class BaseVOContent {

    private static final String CONTENT = """
            package {{package}}.vo;

            import com.beicroon.construct.annotation.ApiModel;
            import com.beicroon.construct.annotation.ApiModelProperty;
            import com.beicroon.construct.entity.GenericVO;
            import lombok.Data;
            import lombok.EqualsAndHashCode;
            {{import}}
            @Data
            @ApiModel(name = "查询{{comment}}基础出参")
            @EqualsAndHashCode(callSuper = true)
            public class {{filename}}BaseVO extends GenericVO {
                {{content}}
            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getEntityPackage() + "." + table.getPackage())
                .replace("{{filename}}", table.getFilename())
                .replace("{{import}}", table.getImportString())
                .replace("{{comment}}", table.getComment())
                .replace("{{content}}", table.getDtoContent());
    }

}
