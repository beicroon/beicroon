package com.beicroon.starter.generic.maker;

import com.beicroon.starter.generic.enums.DBDriverEnums;

public class MysqlTableModuleMaker extends TableModuleMaker {

    public MysqlTableModuleMaker(String host, String database, String username, String password) {
        super(DBDriverEnums.MYSQL, host, database, username, password);
    }

}
