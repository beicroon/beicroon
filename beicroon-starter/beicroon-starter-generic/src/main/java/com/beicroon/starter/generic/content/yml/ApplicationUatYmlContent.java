package com.beicroon.starter.generic.content.yml;

public class ApplicationUatYmlContent {

    private static final String CONTENT = """
            logging:
              level:
                {{basePackage}}: DEBUG
            """;

    public static String getContent(String basePackage) {
        return CONTENT
                .replace("{{basePackage}}", basePackage);
    }

}
