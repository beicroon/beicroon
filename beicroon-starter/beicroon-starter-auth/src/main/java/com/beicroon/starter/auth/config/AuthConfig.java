package com.beicroon.starter.auth.config;

import com.beicroon.starter.auth.interceptor.AuthInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@Import(AuthInterceptor.class)
public class AuthConfig implements WebMvcConfigurer {

    @Resource
    private AuthInterceptor authHandler;

    private static final List<String> NO_AUTH = Arrays.asList(
            "/favicon.ico",
            "/**/*.js",
            "/**/*.css",
            "/**/*.html"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(this.authHandler);

        interceptorRegistration.order(1).addPathPatterns("/**").excludePathPatterns(AuthConfig.NO_AUTH);
    }

}
