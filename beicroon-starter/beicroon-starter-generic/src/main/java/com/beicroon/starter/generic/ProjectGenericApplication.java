package com.beicroon.starter.generic;

import com.beicroon.starter.generic.maker.ServiceStructureMaker;

import java.io.File;

public class ProjectGenericApplication {

    private static final String ROOT_PATH = "beicroon-service";

    private static final String MODULE_NAME = "beicroon-service-home";

    private static final String MODULE_VERSION = "1.0.0";

    private static final String MODULE_PACKAGE = "com.beicroon.service.home";

    private static final int SERVER_PORT = 8101;

    private static final int REDIS_DB = 1;

    public static void main(String[] args) {
        ServiceStructureMaker.generic(new File(ROOT_PATH, MODULE_NAME), MODULE_NAME, MODULE_VERSION, MODULE_PACKAGE, SERVER_PORT, REDIS_DB);
    }

}
