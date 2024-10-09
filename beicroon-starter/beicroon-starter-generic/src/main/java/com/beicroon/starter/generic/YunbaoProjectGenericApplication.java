package com.beicroon.starter.generic;

import com.beicroon.starter.generic.maker.ServiceStructureMaker;

import java.io.File;

public class YunbaoProjectGenericApplication {

    private static final String ROOT_PATH = "yunbao-service";

    private static final String SERVICE_NAME = "tms";

    private static final String MODULE_NAME = "yunbao-service-" + SERVICE_NAME;

    private static final String MODULE_VERSION = "1.0.0";

    private static final String MODULE_PACKAGE = "com.yunbao.service." + SERVICE_NAME;

    private static final int SERVER_PORT = 8300;

    private static final int REDIS_DB = 3;

    public static void main(String[] args) {
        ServiceStructureMaker.generic(new File(ROOT_PATH, MODULE_NAME), MODULE_NAME, MODULE_VERSION, MODULE_PACKAGE, SERVER_PORT, REDIS_DB);
    }

}
