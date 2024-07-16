package com.beicroon.starter.gateway.filter;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.utils.RandomUtils;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class TraceFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MDC.put(SystemConstant.TRACE_ID, RandomUtils.uuid());

        return chain.filter(exchange);
    }

}
