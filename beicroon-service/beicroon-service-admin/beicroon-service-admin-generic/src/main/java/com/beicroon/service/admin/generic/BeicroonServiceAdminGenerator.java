package com.beicroon.service.admin.generic;

import com.beicroon.starter.generic.maker.ApiMysqlMaker;

import java.io.File;

public class BeicroonServiceAdminGenerator {

    private static final String ROOT_PATH = "beicroon-service";

    private static final String MODULE_NAME = "beicroon-service-admin";

    private static final String BASE_PACKAGE = "com.beicroon.service.admin";

    private static final String MODULE_PREFIX = "admin_";

    public static void main(String[] args) {
        ApiMysqlMaker maker = new ApiMysqlMaker(
                "127.0.0.1:3306",
                "beicroon-service-admin-dev",
                "root",
                ""
        );

        maker.generic(new File(ROOT_PATH, MODULE_NAME), MODULE_NAME, BASE_PACKAGE, MODULE_PREFIX);
    }

}
