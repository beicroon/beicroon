package com.beicroon.service.home.generic;

import com.beicroon.starter.generic.maker.ApiMysqlMaker;

import java.io.File;

public class BeicroonServiceHomeGenerator {

    private static final String ROOT_PATH = "beicroon-service";

    private static final String SERVICE_NAME = "beicroon-service-home";

    private static final String BASE_PACKAGE = "com.beicroon.service.home";

    private static final String MODULE_PREFIX = "home_";

    public static void main(String[] args) {
        ApiMysqlMaker maker = new ApiMysqlMaker(
                "127.0.0.1:3306",
                "beicroon-service-home",
                "root",
                ""
        );

        maker.generic(new File(ROOT_PATH, SERVICE_NAME), SERVICE_NAME, BASE_PACKAGE, MODULE_PREFIX);
    }

}
