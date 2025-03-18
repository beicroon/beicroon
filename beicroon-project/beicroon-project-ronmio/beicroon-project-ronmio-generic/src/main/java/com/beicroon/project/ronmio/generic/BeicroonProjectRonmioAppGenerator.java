package com.beicroon.project.ronmio.generic;

import com.beicroon.starter.generic.maker.MysqlTableModuleMaker;
import com.beicroon.starter.generic.maker.TableModuleMaker;
import com.beicroon.starter.generic.structure.GenericStructure;

import java.io.File;

public class BeicroonProjectRonmioAppGenerator {

    private static final String SERVICE_PREFIX = "beicroon-service";

    private static final String MODULE_NAME = "ronmio";

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

        TableModuleMaker maker = new MysqlTableModuleMaker(
                "127.0.0.1:3306",
                "dev_" + structure.getDatabaseName(),
                "root",
                ""
        );

        String[] tables = {
                "ronmio_customer",
                "ronmio_packing_list_invoice",
                "ronmio_packing_list_invoice_container_relation",
                "ronmio_packing_list_invoice_record_relation",
                "ronmio_packing_list_invoice_price_relation"
        };

        maker.generic(structure, tables);

        maker.genericVue(new File("beicroon-app", "beicroon-app-ronmio"), tables);
    }

}
