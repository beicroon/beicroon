package com.beicroon.starter.gateway.admin.filter;

import com.beicroon.starter.auth.handler.AuthHandler;
import com.beicroon.starter.auth.property.AuthProperty;
import com.beicroon.starter.gateway.filter.GenericAuthFilter;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AuthProperty.class)
public class AuthFilter extends GenericAuthFilter {

    @Resource
    private AuthHandler authHandler;

    @Override
    protected void authenticate(ServerHttpRequest request, ServerHttpResponse response, ServerHttpRequest.Builder mutate) {
        this.authHandler.authenticate(request, mutate);
    }

}
