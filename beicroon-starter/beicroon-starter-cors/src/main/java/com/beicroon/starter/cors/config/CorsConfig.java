package com.beicroon.starter.cors.config;

import com.beicroon.construct.constant.SystemConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(SystemConstant.ALLOW_METHODS)
                .allowedHeaders(SystemConstant.ALLOW_HEADERS)
                .allowCredentials(false)
                .maxAge(6000);
    }

}

