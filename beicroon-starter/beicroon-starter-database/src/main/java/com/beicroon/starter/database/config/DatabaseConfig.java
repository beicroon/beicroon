package com.beicroon.starter.database.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.beicroon.construct.database.interceptor.DatabaseInterceptor;
import com.beicroon.provider.mybatisplus.injector.MybatisPlusSqlInjector;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Resource
    private List<DatabaseInterceptor> interceptors;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        for (DatabaseInterceptor interceptor : this.interceptors) {
            mybatisPlusInterceptor.addInnerInterceptor((InnerInterceptor) interceptor);
        }

        return mybatisPlusInterceptor;
    }

    @Bean
    public MybatisPlusSqlInjector customSqlInjector() {
        return new MybatisPlusSqlInjector();
    }

}
