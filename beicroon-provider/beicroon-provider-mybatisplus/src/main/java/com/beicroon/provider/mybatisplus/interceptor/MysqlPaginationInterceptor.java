package com.beicroon.provider.mybatisplus.interceptor;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.beicroon.construct.database.interceptor.DatabaseInterceptor;
import org.springframework.stereotype.Component;

@Component
public class MysqlPaginationInterceptor extends PaginationInnerInterceptor implements DatabaseInterceptor {

    public MysqlPaginationInterceptor() {
        super();

        this.setMaxLimit(300L);
        this.setOverflow(false);
        this.setDbType(DbType.MYSQL);
    }

}
