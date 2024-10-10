package com.beicroon.starter.generic.content.yml;

public class ApplicationYmlContent {

    private static final String CONTENT = """
            server:
              port: {{serverPort}}
              shutdown: GRACEFUL

            spring:
              main:
                allow-bean-definition-overriding: true
              application:
                name: @project.parent.artifactId@
              profiles:
                active: @pom.profile.name@
              cache:
                type: REDIS
              data:
                redis:
                  database: {{redisDB}}
                  host: @pom.redis.ip@
                  port: @pom.redis.port@
                  password: @pom.redis.password@
              datasource:
                driver-class-name: com.mysql.cj.jdbc.Driver
                url: jdbc:mysql://@pom.mysql.ip@:@pom.mysql.port@/@pom.profile.name@-@project.parent.artifactId@
                username: @pom.mysql.username@
                password: @pom.mysql.password@
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
              cloud:
                nacos:
                  discovery:
                    server-addr: @pom.nacos.addresses@
                    namespace: @pom.nacos.namespace@
                  config:
                    server-addr: @pom.nacos.addresses@
                    namespace: @pom.nacos.namespace@
                    file-extension: yml
                    enabled: true
                    refresh-enabled: true
            
            logging:
              level:
                com.beicroon: INFO

            mybatis-plus:
              mapper-locations: classpath*:/mapper/*.xml
              type-aliases-package: com.beicroon.service.*.dao.model

            mq:
              server-address: @pom.mq.address@
              namespace: @pom.profile.name@
              group-id: @project.parent.artifactId@
              timeout: 3000

            xxl:
              job:
                admin:
                  addresses: @pom.job.addresses@
            """;

    public static String getContent(int serverPort, int redisDB) {
        return CONTENT
                .replace("{{serverPort}}", String.valueOf(serverPort))
                .replace("{{redisDB}}", String.valueOf(redisDB));
    }

}
