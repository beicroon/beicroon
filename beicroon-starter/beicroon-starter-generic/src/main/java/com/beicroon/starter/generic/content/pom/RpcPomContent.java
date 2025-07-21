package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class RpcPomContent {

    private static final String CONTENT = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>

                <parent>
                    <groupId>{{commonPackage}}</groupId>
                    <artifactId>{{commonName}}</artifactId>
                    <version>1.0.0.Release</version>
                </parent>

                <artifactId>{{commonName}}-rpc</artifactId>
                <version>1.0.0.Release</version>
                <packaging>jar</packaging>

                <dependencies>
                    <dependency>
                        <groupId>com.beicroon.starter</groupId>
                        <artifactId>beicroon-starter-feign</artifactId>
                    </dependency>
                </dependencies>
            </project>
            """;

    private RpcPomContent() {

    }

    public static String getContent(GenericStructure structure) {
        return CONTENT
                .replace("{{commonPackage}}", structure.getCommonPackage())
                .replace("{{commonName}}", structure.getCommonName())
                ;
    }

}
