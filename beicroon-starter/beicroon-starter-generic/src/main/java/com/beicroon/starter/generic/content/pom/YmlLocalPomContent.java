package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class YmlLocalPomContent {

    private static final String CONTENT = """
            spring:
              datasource:
                url: jdbc:mysql://127.0.0.1:3306/dev_{{databaseName}}
                username: root
                password:
              flyway:
                enabled: false
            
            logging:
              level:
                {{basePackage}}: DEBUG
            """;

    private YmlLocalPomContent() {

    }

    public static String getContent(GenericStructure structureManager) {
        return CONTENT
                .replace("{{databaseName}}", structureManager.getDatabaseName())
                .replace("{{basePackage}}", structureManager.getBasePackage())
                ;
    }

}
