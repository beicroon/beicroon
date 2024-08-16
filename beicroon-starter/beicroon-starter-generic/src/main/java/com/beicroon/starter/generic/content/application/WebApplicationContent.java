package com.beicroon.starter.generic.content.application;

public class WebApplicationContent {

    private static final String CONTENT = """
            package {{basePackage}}.web;

            import com.beicroon.starter.web.annotation.BeicroonBootApplication;
            import org.springframework.boot.SpringApplication;

            @BeicroonBootApplication
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
