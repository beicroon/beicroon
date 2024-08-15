package com.beicroon.starter.generic.content.manager;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class ManagerContent {

    private static final String CONTENT = """
            package {{package}}.manager;

            import {{package}}.convertor.{{filename}}Convertor;
            import {{package}}.repository.{{filename}}Repository;
            import jakarta.annotation.Resource;
            import lombok.extern.slf4j.Slf4j;
            import org.springframework.stereotype.Component;

            @Slf4j
            @Component
            public class {{filename}}Manager {

                 @Resource
                 private {{filename}}Convertor {{variableName}}Convertor;

                 @Resource
                 private {{filename}}Repository {{variableName}}Repository;

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replaceAll("\\{\\{package}}", packageManager.getDaoPackage())
                .replaceAll("\\{\\{filename}}", table.getFilename())
                .replaceAll("\\{\\{variableName}}", table.getVariableName());
    }

}
