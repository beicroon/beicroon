package com.beicroon.starter.cors.config;

import com.beicroon.starter.cors.filter.AuthFilter;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@Import(AuthFilter.class)
public class WebAuthConfig implements WebMvcConfigurer, Ordered {

    @Resource
    private AuthFilter authFilter;

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void addInterceptors(@Nonnull InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(this.authFilter);

        interceptorRegistration.order(0).addPathPatterns("/**");
    }

}
