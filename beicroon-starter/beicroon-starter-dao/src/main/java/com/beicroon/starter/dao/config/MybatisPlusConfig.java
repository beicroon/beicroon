package com.beicroon.starter.dao.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.beicroon.provider.mybatisplus.interceptor.DataPermissionInterceptor;
import com.beicroon.provider.mybatisplus.interceptor.MysqlPaginationInterceptor;
import com.beicroon.provider.mybatisplus.interceptor.RemoveFilterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        mybatisPlusInterceptor.addInnerInterceptor(new RemoveFilterInterceptor());
        mybatisPlusInterceptor.addInnerInterceptor(new DataPermissionInterceptor());
        mybatisPlusInterceptor.addInnerInterceptor(new MysqlPaginationInterceptor());

        return mybatisPlusInterceptor;
    }

}
