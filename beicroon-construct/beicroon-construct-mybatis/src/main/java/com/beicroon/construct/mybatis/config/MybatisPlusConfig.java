package com.beicroon.construct.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.beicroon.construct.mybatis.interceptor.DataPermissionInterceptor;
import com.beicroon.construct.mybatis.interceptor.MysqlPaginationInterceptor;
import com.beicroon.construct.mybatis.interceptor.RemoveInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        interceptor.addInnerInterceptor(new RemoveInterceptor());
        interceptor.addInnerInterceptor(new DataPermissionInterceptor());
        interceptor.addInnerInterceptor(new MysqlPaginationInterceptor());

        return interceptor;
    }

}
