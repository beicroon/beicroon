package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class WebPomContent {

    private static final String CONTENT = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>

                <parent>
                    <groupId>{{basePackage}}</groupId>
                    <artifactId>{{moduleName}}</artifactId>
                    <version>1.0.0.Final</version>
                </parent>

                <artifactId>{{moduleName}}-web</artifactId>
                <version>1.0.0.Final</version>
                <packaging>jar</packaging>

                <dependencies>
                    <dependency>
                        <groupId>{{basePackage}}</groupId>
                        <artifactId>{{moduleName}}-app</artifactId>
                        <version>${project.version}</version>
                    </dependency>

                    <dependency>
                        <groupId>com.beicroon.starter</groupId>
                        <artifactId>beicroon-starter-web</artifactId>
                    </dependency>

                    <dependency>
                        <groupId>junit</groupId>
                        <artifactId>junit</artifactId>
                        <scope>test</scope>
                    </dependency>

                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-test</artifactId>
                        <scope>test</scope>
                    </dependency>
                </dependencies>
            </project>
            """;

    private WebPomContent() {

    }

    public static String getContent(GenericStructure structure) {
        return CONTENT
                .replace("{{basePackage}}", structure.getBasePackage())
                .replace("{{moduleName}}", structure.getServiceName())
                ;
    }

}
