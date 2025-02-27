package com.beicroon.starter.gateway.handler;

import com.beicroon.construct.base.entity.Result;
import com.beicroon.construct.base.entity.WebResult;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.exception.BusinessException;
import com.beicroon.construct.json.utils.JsonUtils;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalExceptionHandler implements ErrorWebExceptionHandler, Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    @Nonnull
    @Override
    public Mono<Void> handle(@Nonnull ServerWebExchange exchange, @Nonnull Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        if (ex instanceof BusinessException bex) {
            response.setStatusCode(HttpStatus.OK);

            return this.fail(response, WebResult.error(bex.getCode(), ex.getMessage()));
        }

        if (ex instanceof ResponseStatusException) {
            if (ex instanceof NotFoundException) {
                response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);

                return this.fail(response, WebResult.error(503, "服务不可用:请稍后再试！"));
            }

            HttpStatusCode statusCode = ((ResponseStatusException) ex).getStatusCode();

            response.setStatusCode(statusCode);

            return this.fail(response, WebResult.error(statusCode.value(), statusCode.toString()));
        }

        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        String message = String.format("%s:%s", ex.getClass().getName(), ex.getMessage());

        log.error(String.format("系统异常=>%s", message), ex);

        return this.fail(response, WebResult.error(500, "系统异常:请稍后再试！"));
    }

    private Mono<Void> fail(ServerHttpResponse response, Result<?> result) {
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        String message = JsonUtils.toJson(result);

        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(SystemConstant.CHARSET));

        return response.writeWith(Mono.just(buffer));
    }

}
