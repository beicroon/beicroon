package com.beicroon.starter.generic.content.application;

public class GenericApplicationContent {

    private static final String CONTENT = """
            package {{basePackage}}.generic;

            import com.beicroon.starter.generic.maker.ModuleMysqlMaker;
            import com.beicroon.starter.generic.utils.FileUtils;

            import java.io.File;

            public class {{filename}}Generator {

                private static final String ROOT_PATH = "";

                private static final String MODULE_NAME = "{{moduleName}}";

                private static final String BASE_PACKAGE = "{{basePackage}}";

                private static final String MODULE_PREFIX = "";

                private static final String HOME_PATH = FileUtils.joinPaths("app", "src", "modules");

                public static void main(String[] args) {
                    ModuleMysqlMaker maker = new ModuleMysqlMaker(
                            "127.0.0.1:3306",
                            "dev-{{moduleName}}",
                            "root",
                            ""
                    );

                    maker.generic(new File(ROOT_PATH, MODULE_NAME), MODULE_NAME, BASE_PACKAGE, MODULE_PREFIX);

                    maker.genericVueModule(new File(HOME_PATH), MODULE_PREFIX);
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
