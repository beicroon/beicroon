package com.beicroon.starter.generic;

import com.beicroon.starter.generic.maker.ServiceStructureMaker;
import com.beicroon.starter.generic.structure.GenericStructure;

import java.io.File;

public class BeicroonProjectGenericApplication {

    private static final String SERVICE_PREFIX = "beicroon-service";

    private static final String MODULE_NAME = "banner";

    private static final String BASE_PACKAGE = "com.beicroon.service";

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        GenericStructure structure = new GenericStructure(
                new File(SERVICE_PREFIX, String.format("%s-%s", SERVICE_PREFIX, MODULE_NAME)),
                SERVICE_PREFIX,
                MODULE_NAME,
                BASE_PACKAGE,
                SERVER_PORT
        );

        ServiceStructureMaker.generic(structure);
    }

}
