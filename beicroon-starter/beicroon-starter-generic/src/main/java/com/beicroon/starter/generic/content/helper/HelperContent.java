package com.beicroon.starter.generic.content.helper;

import com.beicroon.starter.generic.entity.Table;
import com.beicroon.starter.generic.manager.PackageManager;

public class HelperContent {

    private static final String CONTENT = """
            package {{package}}.helper;
                        
            import lombok.extern.slf4j.Slf4j;

            @Slf4j
            public final class {{filename}}Helper {

                private {{filename}}Helper() {

                }

            }
            """;

    public static String getContent(PackageManager packageManager, Table table) {
        return CONTENT
                .replace("{{package}}", packageManager.getDaoPackage())
                .replace("{{filename}}", table.getFilename());
    }

}
