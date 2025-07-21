package com.beicroon.module.map.generic;

import com.beicroon.starter.generic.maker.MysqlTableModuleMaker;
import com.beicroon.starter.generic.maker.TableModuleMaker;
import com.beicroon.starter.generic.structure.GenericStructure;

import java.io.File;

public class BeicroonModuleMapAppGenerator {

    private static final String SERVICE_PREFIX = "beicroon-module";

    private static final String COMMON_PREFIX = "beicroon-common";

    private static final String MODULE_NAME = "map";

    private static final String BASE_PACKAGE = "com.beicroon.module";

    public static void main(String[] args) {
        GenericStructure structure = new GenericStructure(
                new File(SERVICE_PREFIX, String.format("%s-%s", SERVICE_PREFIX, MODULE_NAME)),
                SERVICE_PREFIX,
                COMMON_PREFIX,
                MODULE_NAME,
                BASE_PACKAGE
        );

        TableModuleMaker maker = new MysqlTableModuleMaker(
                "127.0.0.1:3306",
                "dev_" + structure.getDatabaseName(),
                "root",
                ""
        );

        maker.generic(structure);
    }

}
