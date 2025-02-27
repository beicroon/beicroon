package com.beicroon.service.admin.generic;

import com.beicroon.starter.generic.maker.MysqlTableModuleMaker;
import com.beicroon.starter.generic.maker.TableModuleMaker;
import com.beicroon.starter.generic.structure.GenericStructure;

import java.io.File;

public class BeicroonServiceAdminAppGenerator {

    private static final String SERVICE_PREFIX = "beicroon-service";

    private static final String MODULE_NAME = "admin";

    private static final String BASE_PACKAGE = "com.beicroon.service";

    private static final int SERVER_PORT = 8100;

    public static void main(String[] args) {
        GenericStructure structure = new GenericStructure(
                new File(SERVICE_PREFIX, String.format("%s-%s", SERVICE_PREFIX, MODULE_NAME)),
                SERVICE_PREFIX,
                MODULE_NAME,
                BASE_PACKAGE,
                SERVER_PORT
        );

        TableModuleMaker maker = new MysqlTableModuleMaker(
                "127.0.0.1:3306",
//                "dev_" + structure.getDatabaseName(),
                "dev_beicroon_service_ronmio",
                "root",
                ""
        );

        String[] tables = {
                "admin_resource",
                "admin_role",
                "admin_account",
                "admin_account_role_relation",
                "admin_role_resource_relation"
        };

        maker.generic(structure, tables);

        maker.genericVue(new File("beicroon-app", "beicroon-app-ronmio"), tables);
    }

}
