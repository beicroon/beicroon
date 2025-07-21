package com.beicroon.starter.gateway.filter;

import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.utils.UrlUtils;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public abstract class GenericAuthFilter implements GlobalFilter, Ordered {

    protected abstract void authenticate(ServerHttpRequest request, ServerHttpResponse response, ServerHttpRequest.Builder mutate);

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        ServerHttpResponse response = exchange.getResponse();

        ServerHttpRequest.Builder mutate = request.mutate();

        mutate.header(HeaderConstant.TRACE_ID, UrlUtils.encode(MDC.get(SystemConstant.TRACE_ID)));

        this.authenticate(request, response, mutate);

        MDC.clear();

        return chain.filter(exchange);
    }

}
