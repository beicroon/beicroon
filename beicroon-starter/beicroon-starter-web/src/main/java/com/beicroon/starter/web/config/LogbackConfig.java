package com.beicroon.starter.web.config;

import com.beicroon.starter.web.interceptor.LogbackInterceptor;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@Import(LogbackInterceptor.class)
public class LogbackConfig implements WebMvcConfigurer {

    @Resource
    private LogbackInterceptor logbackInterceptor;

    @Override
    public void addInterceptors(@Nonnull InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(this.logbackInterceptor);

        interceptorRegistration.order(1).addPathPatterns("/**");
    }

}
