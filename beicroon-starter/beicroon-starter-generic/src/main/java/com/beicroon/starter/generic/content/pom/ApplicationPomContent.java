package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class ApplicationPomContent {

    private static final String CONTENT = """
            package {{modulePackage}}.web;

            import com.beicroon.construct.constant.SystemConstant;
            import org.mybatis.spring.annotation.MapperScan;
            import org.springframework.boot.SpringApplication;
            import org.springframework.boot.autoconfigure.SpringBootApplication;
            import org.springframework.context.annotation.ComponentScan;

            @SpringBootApplication
            @MapperScan(basePackages = {SystemConstant.PACKAGE_MAPPER})
            @ComponentScan(basePackages = {SystemConstant.PACKAGE_BASE})
            public class {{className}}WebApplication {

                public static void main(String[] args) {
                    SpringApplication.run({{className}}WebApplication.class, args);
                }

            }
            """;

    private ApplicationPomContent() {

    }

    public static String getContent(GenericStructure structure) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", structure.getClassName())
                ;
    }

}
