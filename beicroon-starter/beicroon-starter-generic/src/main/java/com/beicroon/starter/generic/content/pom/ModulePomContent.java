package com.beicroon.starter.generic.content.pom;

public class ModulePomContent {

    private static final String CONTENT = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>

                <parent>
                    <groupId>com.beicroon.starter</groupId>
                    <artifactId>beicroon-starter</artifactId>
                    <version>{{moduleVersion}}</version>
                    <relativePath/>
                </parent>

                <groupId>com.beicroon.service</groupId>
                <artifactId>{{moduleName}}</artifactId>
                <version>{{moduleVersion}}</version>
                <packaging>pom</packaging>

                <!--  请勿随意调整顺序  -->
                <modules>
                    <module>{{moduleName}}-dao</module>
                    <module>{{moduleName}}-entity</module>
                    <module>{{moduleName}}-generic</module>
                    <module>{{moduleName}}-web</module>
                </modules>
            </project>
            """;

    public static String getContent(String moduleName, String moduleVersion) {
        return CONTENT
                .replace("{{moduleName}}", moduleName)
                .replace("{{moduleVersion}}", moduleVersion);
    }

}
