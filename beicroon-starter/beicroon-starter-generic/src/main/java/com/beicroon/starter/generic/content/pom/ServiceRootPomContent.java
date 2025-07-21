package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class ServiceRootPomContent {

    private static final String CONTENT = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>

                <parent>
                    <groupId>com.beicroon.starter</groupId>
                    <artifactId>beicroon-starter</artifactId>
                    <version>1.0.0.Release</version>
                    <relativePath/>
                </parent>

                <groupId>{{basePackage}}</groupId>
                <artifactId>{{moduleName}}</artifactId>
                <version>1.0.0.Release</version>
                <packaging>pom</packaging>

                <modules>
                    <module>{{moduleName}}-app</module>
                    <module>{{moduleName}}-dao</module>
                    <module>{{moduleName}}-entity</module>
                    <module>{{moduleName}}-generic</module>
                    <module>{{moduleName}}-web</module>
                </modules>
            </project>
            """;

    private ServiceRootPomContent() {

    }

    public static String getContent(GenericStructure structure) {
        return CONTENT
                .replace("{{basePackage}}", structure.getBasePackage())
                .replace("{{moduleName}}", structure.getServiceName())
                ;
    }

}
