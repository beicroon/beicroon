package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class YmlPomContent {

    private static final String CONTENT = """
            server:
              port: {{serverPort}}
              shutdown: GRACEFUL
            
            spring:
              main:
                allow-bean-definition-overriding: true
              application:
                name: @project.artifactId@
              cache:
                type: REDIS
              data:
                redis:
                  database: 0
                  host: 127.0.0.1
                  port: 6379
                  password:
              datasource:
                type: com.alibaba.druid.pool.DruidDataSource
                driver-class-name: com.mysql.cj.jdbc.Driver
                url: jdbc:mysql://127.0.0.1:3306/{{databaseName}}
                username: root
                password:
              flyway:
                enabled: true
                baseline-on-migrate: false
                encoding: UTF-8
                locations: classpath:migration
                sql-migration-prefix: V
                sql-migration-separator: _
                sql-migration-suffixes: .sql
                validate-on-migrate: false
                clean-disabled: false
            
            logging:
              level:
                {{basePackage}}: INFO
            """;

    private YmlPomContent() {

    }

    public static String getContent(GenericStructure structureManager) {
        return CONTENT
                .replace("{{serverPort}}", structureManager.getServerPort())
                .replace("{{databaseName}}", structureManager.getDatabaseName())
                .replace("{{basePackage}}", structureManager.getBasePackage())
                ;
    }

}
