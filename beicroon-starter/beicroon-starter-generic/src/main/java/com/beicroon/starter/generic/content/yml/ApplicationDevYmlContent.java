package com.beicroon.starter.generic.content.yml;

public class ApplicationDevYmlContent {

    private static final String CONTENT = """
            logging:
              level:
                root: DEBUG
            
            mybatis-plus:
              configuration:
                log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
            """;

    public static String getContent() {
        return CONTENT;
    }

}
