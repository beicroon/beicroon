package com.beicroon.starter.generic.content.pom;

public class DaoPomContent {

    private static final String CONTENT = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>

                <parent>
                    <groupId>com.beicroon.service</groupId>
                    <artifactId>{{moduleName}}</artifactId>
                    <version>{{moduleVersion}}</version>
                </parent>

                <artifactId>{{moduleName}}-dao</artifactId>
                <version>{{moduleVersion}}</version>
                <packaging>jar</packaging>

                <dependencies>
                    <dependency>
                        <groupId>com.beicroon.starter</groupId>
                        <artifactId>beicroon-starter-dao</artifactId>
                    </dependency>

                    <dependency>
                        <groupId>com.beicroon.service</groupId>
                        <artifactId>{{moduleName}}-entity</artifactId>
                        <version>${project.version}</version>
                    </dependency>
                </dependencies>
            </project>
            """;

    public static String getContent(String moduleName, String moduleVersion) {
        return CONTENT
                .replace("{{moduleName}}", moduleName)
                .replace("{{moduleVersion}}", moduleVersion);
    }

}
