package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class YmlProPomContent {

    private static final String CONTENT = """
            spring:
              datasource:
                url: jdbc:mysql://127.0.0.1:3306/{{databaseName}}
                username: root
                password:
              flyway:
                enabled: false
            
            logging:
              level:
                {{basePackage}}: INFO
            """;

    private YmlProPomContent() {

    }

    public static String getContent(GenericStructure structureManager) {
        return CONTENT
                .replace("{{databaseName}}", structureManager.getDatabaseName())
                .replace("{{basePackage}}", structureManager.getBasePackage())
                ;
    }

}
