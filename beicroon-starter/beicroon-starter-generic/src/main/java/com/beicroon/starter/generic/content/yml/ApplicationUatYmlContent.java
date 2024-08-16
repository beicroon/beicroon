package com.beicroon.starter.generic.content.yml;

public class ApplicationUatYmlContent {

    private static final String CONTENT = """
            logging:
              level:
                root: DEBUG
            """;

    public static String getContent() {
        return CONTENT;
    }

}
