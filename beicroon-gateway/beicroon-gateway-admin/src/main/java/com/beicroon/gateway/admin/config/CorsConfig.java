package com.beicroon.gateway.admin.config;

import com.beicroon.construct.constant.HeaderConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("Accept");
        config.addAllowedHeader("Referer");
        config.addAllowedHeader("User-Agent");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader(HeaderConstant.SOURCE);
        config.addAllowedHeader(HeaderConstant.VERSION);
        config.addAllowedHeader(HeaderConstant.TENANT_ID);
        config.addAllowedHeader(HeaderConstant.AUTHORIZATION_TOKEN);
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());

        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}