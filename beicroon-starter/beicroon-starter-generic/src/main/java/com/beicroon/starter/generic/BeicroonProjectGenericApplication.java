package com.beicroon.starter.generic;

import com.beicroon.starter.generic.maker.CommonStructureMaker;
import com.beicroon.starter.generic.maker.ServiceStructureMaker;
import com.beicroon.starter.generic.structure.GenericStructure;

import java.io.File;

public class BeicroonProjectGenericApplication {

    private static final String COMMON_PREFIX = "beicroon-common";

    private static final String SERVICE_PREFIX = "beicroon-module";

    private static final String MODULE_NAME = "user";

    private static final String BASE_PACKAGE = "com.beicroon.module";

    private static final String COMMON_PACKAGE = "com.beicroon.common";

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        GenericStructure serviceStructure = new GenericStructure(
                new File(SERVICE_PREFIX, String.format("%s-%s", SERVICE_PREFIX, MODULE_NAME)),
                SERVICE_PREFIX,
                COMMON_PREFIX,
                MODULE_NAME,
                BASE_PACKAGE,
                COMMON_PACKAGE,
                SERVER_PORT
        );

        ServiceStructureMaker.generic(serviceStructure);

        GenericStructure commonStructure = new GenericStructure(
                new File(COMMON_PREFIX, String.format("%s-%s", COMMON_PREFIX, MODULE_NAME)),
                COMMON_PREFIX,
                COMMON_PREFIX,
                MODULE_NAME,
                BASE_PACKAGE,
                COMMON_PACKAGE,
                SERVER_PORT
        );

        CommonStructureMaker.generic(commonStructure);
    }

}
