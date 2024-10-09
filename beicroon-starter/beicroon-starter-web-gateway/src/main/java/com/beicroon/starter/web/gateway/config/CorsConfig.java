package com.beicroon.starter.web.gateway.config;

import com.beicroon.construct.constant.HeaderConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer, Ordered {

    private static final String[] HEADERS = {
            "Accept",
            "Referer",
            "User-Agent",
            "Content-Type",
            HeaderConstant.SOURCE,
            HeaderConstant.VERSION,
            HeaderConstant.TENANT_ID,
            HeaderConstant.AUTHORIZATION_TOKEN,
    };

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(HEADERS)
                .allowCredentials(false)
                .maxAge(6000);
    }

}

