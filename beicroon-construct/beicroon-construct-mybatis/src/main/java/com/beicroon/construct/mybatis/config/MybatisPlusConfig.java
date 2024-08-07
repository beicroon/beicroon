package com.beicroon.construct.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.beicroon.construct.mybatis.interceptor.DataPermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        interceptor.addInnerInterceptor(new DataPermissionInterceptor());

        PaginationInnerInterceptor pagination = new PaginationInnerInterceptor();

        pagination.setMaxLimit(300L);
        pagination.setOverflow(false);
        pagination.setDbType(DbType.MYSQL);

        interceptor.addInnerInterceptor(pagination);

        return interceptor;
    }

}
