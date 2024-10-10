package com.beicroon.starter.generic.content.application;

public class WebApplicationContent {

    private static final String CONTENT = """
            package {{basePackage}}.web;

            import com.beicroon.construct.constant.SystemConstant;
            import com.beicroon.starter.web.admin.annotation.BeicroonWebAdminBootApplication;
            import org.mybatis.spring.annotation.MapperScan;
            import org.springframework.boot.SpringApplication;
            import org.springframework.context.annotation.ComponentScan;

            @BeicroonWebBootApplication
            @MapperScan({SystemConstant.MAPPER_PACKAGE})
            @ComponentScan({SystemConstant.BASE_PACKAGE})
            public class {{filename}}Application {

                public static void main(String[] args) {
                    SpringApplication.run({{filename}}Application.class, args);
                }

            }
            """;

    public static String getContent(String filename, String basePackage) {
        return CONTENT
                .replace("{{filename}}", filename)
                .replace("{{basePackage}}", basePackage);
    }

}
