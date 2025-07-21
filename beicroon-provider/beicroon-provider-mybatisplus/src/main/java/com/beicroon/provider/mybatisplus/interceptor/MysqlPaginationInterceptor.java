package com.beicroon.provider.mybatisplus.interceptor;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

public class MysqlPaginationInterceptor extends PaginationInnerInterceptor {

    public MysqlPaginationInterceptor() {
        super();

        this.setMaxLimit(300L);
        this.setOverflow(false);
        this.setDbType(DbType.MYSQL);
    }

}
