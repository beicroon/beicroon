package com.beicroon.service.admin.generic;

import com.beicroon.starter.generic.maker.ModuleMysqlMaker;
import com.beicroon.starter.generic.utils.FileUtils;

import java.io.File;

public class BeicroonServiceAdminGenerator {

    private static final String ROOT_PATH = "beicroon-service";

    private static final String MODULE_NAME = "beicroon-service-admin";

    private static final String BASE_PACKAGE = "com.beicroon.service.admin";

    private static final String MODULE_PREFIX = "admin";

    private static final String HOME_PATH = FileUtils.joinPaths("beicroon-app", "beicroon-app-admin", "src", "modules");

    public static void main(String[] args) {
        ModuleMysqlMaker maker = new ModuleMysqlMaker(
                "127.0.0.1:3306",
                "dev-beicroon-service-admin",
                "root",
                ""
        );

        maker.generic(new File(ROOT_PATH, MODULE_NAME), MODULE_NAME, BASE_PACKAGE, MODULE_PREFIX);

        maker.genericVueModule(new File(HOME_PATH), MODULE_PREFIX);
    }

}
