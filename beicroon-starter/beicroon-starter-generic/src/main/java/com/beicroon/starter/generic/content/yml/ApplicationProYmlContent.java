package com.beicroon.starter.generic.content.yml;

public class ApplicationProYmlContent {

    private static final String CONTENT = """
            logging:
              level:
                {{basePackage}}: INFO
            """;

    public static String getContent(String basePackage) {
        return CONTENT
                .replace("{{basePackage}}", basePackage);
    }

}
