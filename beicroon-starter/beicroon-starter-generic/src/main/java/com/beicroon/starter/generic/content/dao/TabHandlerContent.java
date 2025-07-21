package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class TabHandlerContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.tab;

            import com.beicroon.starter.dao.handler.TabHandler;
            import {{modulePackage}}.dao.model.{{className}}Model;

            public interface {{className}}TabHandler extends TabHandler<{{className}}Model> {

            }
            """;

    private TabHandlerContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
