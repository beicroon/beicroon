package com.beicroon.starter.generic.content.pom;

import com.beicroon.starter.generic.structure.GenericStructure;

public final class GeneratorContent {

    private static final String CONTENT = """
            package {{modulePackage}}.generic;

            import com.beicroon.starter.generic.maker.MysqlTableModuleMaker;
            import com.beicroon.starter.generic.maker.TableModuleMaker;
            import com.beicroon.starter.generic.structure.GenericStructure;

            import java.io.File;

            public class {{className}}AppGenerator {

                private static final String SERVICE_PREFIX = "{{servicePrefix}}";

                private static final String COMMON_PREFIX = "{{commonPrefix}}";

                private static final String MODULE_NAME = "{{moduleName}}";

                private static final String BASE_PACKAGE = "{{basePackage}}";

                public static void main(String[] args) {
                    GenericStructure structure = new GenericStructure(
                            new File(SERVICE_PREFIX, String.format("%s-%s", SERVICE_PREFIX, MODULE_NAME)),
                            SERVICE_PREFIX,
                            COMMON_PREFIX,
                            MODULE_NAME,
                            BASE_PACKAGE
                    );

                    TableModuleMaker maker = new MysqlTableModuleMaker(
                            "127.0.0.1:3306",
                            "dev_" + structure.getDatabaseName(),
                            "root",
                            ""
                    );

                    maker.generic(structure);
                }

            }
            """;

    private GeneratorContent() {

    }

    public static String getContent(GenericStructure structure) {
        return CONTENT
                .replace("{{modulePackage}}", structure.getModulePackage())
                .replace("{{className}}", structure.getClassName())
                .replace("{{servicePrefix}}", structure.getServicePrefix())
                .replace("{{moduleName}}", structure.getModuleName())
                .replace("{{basePackage}}", structure.getBasePackage())
                .replace("{{commonPrefix}}", structure.getCommonPrefix())
                ;
    }

}
