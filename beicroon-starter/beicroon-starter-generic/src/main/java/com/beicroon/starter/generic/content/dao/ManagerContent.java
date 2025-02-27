package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class ManagerContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.manager;

            import {{modulePackage}}.dao.convertor.{{className}}Convertor;
            import {{modulePackage}}.dao.repository.{{className}}Repository;
            import jakarta.annotation.Resource;
            import lombok.extern.slf4j.Slf4j;
            import org.springframework.stereotype.Component;

            @Slf4j
            @Component
            public class {{className}}Manager {

                @Resource
                private {{className}}Convertor {{variableName}}Convertor;

                @Resource
                private {{className}}Repository {{variableName}}Repository;

            }
            """;

    private ManagerContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", table.getClassName())
                .replace("{{variableName}}", table.getVariableName())
                ;
    }
    
}
