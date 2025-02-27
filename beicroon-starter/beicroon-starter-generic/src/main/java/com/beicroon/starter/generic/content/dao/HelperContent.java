package com.beicroon.starter.generic.content.dao;

import com.beicroon.starter.generic.database.Table;
import com.beicroon.starter.generic.structure.GenericStructure;

public final class HelperContent {

    private static final String CONTENT = """
            package {{modulePackage}}.dao.helper;

            public final class {{className}}Helper {

                private {{className}}Helper() {
            
                }

            }
            """;

    private HelperContent() {
    }

    public static String getContent(GenericStructure structure, Table table) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", table.getClassName())
                ;
    }
    
}
