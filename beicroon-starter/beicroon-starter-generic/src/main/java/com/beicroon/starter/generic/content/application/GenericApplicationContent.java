package com.beicroon.starter.generic.content.application;

public class GenericApplicationContent {

    private static final String CONTENT = """
            package {{basePackage}}.generic;

            import com.beicroon.starter.generic.maker.ApiMysqlMaker;

            import java.io.File;

            public class {{filename}}Generator {

                private static final String ROOT_PATH = "";

                private static final String SERVICE_NAME = "{{moduleName}}";

                private static final String BASE_PACKAGE = "{{basePackage}}";

                private static final String MODULE_PREFIX = "";

                public static void main(String[] args) {
                    ApiMysqlMaker maker = new ApiMysqlMaker(
                            "127.0.0.1:3306",
                            "dev-{{moduleName}}",
                            "root",
                            ""
                    );

                    maker.generic(new File(ROOT_PATH, SERVICE_NAME), SERVICE_NAME, BASE_PACKAGE, MODULE_PREFIX);
                }

            }
            """;

    public static String getContent(String filename, String moduleName, String basePackage) {
        return CONTENT
                .replace("{{filename}}", filename)
                .replace("{{moduleName}}", moduleName)
                .replace("{{basePackage}}", basePackage);
    }

}
