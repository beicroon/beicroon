package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class DaoPomContent {

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

                <artifactId>{{moduleName}}-dao</artifactId>
                <version>1.0.0.Final</version>
                <packaging>jar</packaging>

                <dependencies>
                    <dependency>
                        <groupId>{{basePackage}}</groupId>
                        <artifactId>{{moduleName}}-entity</artifactId>
                        <version>${project.version}</version>
                    </dependency>

                    <dependency>
                        <groupId>com.beicroon.starter</groupId>
                        <artifactId>beicroon-starter-dao</artifactId>
                    </dependency>
                </dependencies>
            </project>
            """;

    private DaoPomContent() {

    }

    public static String getContent(GenericStructure structure) {
        return CONTENT
                .replace("{{basePackage}}", structure.getBasePackage())
                .replace("{{moduleName}}", structure.getServiceName())
                ;
    }

}
