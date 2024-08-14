package com.beicroon.service.admin.generic;

import com.beicroon.starter.generic.maker.ApiMysqlMaker;

public class BeicroonServiceAdminGenerator {

    public static void main(String[] args) {
        ApiMysqlMaker maker = new ApiMysqlMaker(
                "127.0.0.1:3306",
                "beicroon-service-admin-dev",
                "root",
                ""
        );

        maker.generic(BeicroonServiceAdminGenerator.class);
    }

}
