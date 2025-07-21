package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class TestPomContent {

    private static final String CONTENT = """
            package {{modulePackage}}.web;

            import lombok.extern.slf4j.Slf4j;
            import org.junit.Test;
            import org.junit.runner.RunWith;
            import org.springframework.boot.test.context.SpringBootTest;
            import org.springframework.test.context.ActiveProfiles;
            import org.springframework.test.context.junit4.SpringRunner;

            @Slf4j
            @SpringBootTest
            @ActiveProfiles("local")
            @RunWith(SpringRunner.class)
            public class {{className}}WebTests {

                @Test
                public void test() {

                }

            }
            """;

    private TestPomContent() {

    }

    public static String getContent(GenericStructure structure) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", structure.getClassName())
                ;
    }

}
