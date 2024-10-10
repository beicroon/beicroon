package com.beicroon.starter.generic.content.yml;

public class ApplicationLocalYmlContent {

    private static final String CONTENT = """
            spring:
              datasource:
                driver-class-name: com.mysql.cj.jdbc.Driver
                url: jdbc:mysql://@pom.mysql.ip@:@pom.mysql.port@/dev-@project.parent.artifactId@
                username: @pom.mysql.username@
                password: @pom.mysql.password@
              flyway:
                enabled: false
              cloud:
                nacos:
                  discovery:
                    server-addr: @pom.nacos.addresses@
                    namespace: local

            logging:
              level:
                com.beicroon: DEBUG

            mybatis-plus:
              configuration:
                log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
            """;

    public static String getContent(String basePackage) {
        return CONTENT
                .replace("{{basePackage}}", basePackage);
    }

}
