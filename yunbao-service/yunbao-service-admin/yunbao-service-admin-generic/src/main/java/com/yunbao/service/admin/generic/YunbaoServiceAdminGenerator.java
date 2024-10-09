package com.yunbao.service.admin.generic;

import com.beicroon.starter.generic.maker.ModuleMysqlMaker;
import com.beicroon.starter.generic.utils.FileUtils;

import java.io.File;

public class YunbaoServiceAdminGenerator {

    private static final String ROOT_PATH = "yunbao-service";

    private static final String MODULE_NAME = "yunbao-service-admin";

    private static final String BASE_PACKAGE = "com.yunbao.service.admin";

    private static final String MODULE_PREFIX = "admin";

    private static final String HOME_PATH = FileUtils.joinPaths("yunbao-app", "yunbao-app-admin", "src", "modules");

    public static void main(String[] args) {
        ModuleMysqlMaker maker = new ModuleMysqlMaker(
                "127.0.0.1:3306",
                "dev-yunbao-service-admin",
                "root",
                ""
        );

        maker.generic(new File(ROOT_PATH, MODULE_NAME), MODULE_NAME, BASE_PACKAGE, MODULE_PREFIX);

        maker.genericVueModule(new File(HOME_PATH), MODULE_PREFIX);
    }

}
