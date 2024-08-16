package com.beicroon.starter.generic.content.yml;

public class ApplicationProYmlContent {

    private static final String CONTENT = """
            logging:
              level:
                root: INFO
            """;

    public static String getContent() {
        return CONTENT;
    }

}
