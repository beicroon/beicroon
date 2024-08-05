package com.beicroon.gateway.admin.filter;

import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.construct.constant.JwtConstant;
import com.beicroon.construct.entity.AuthUser;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.jackson.utils.JsonUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.UrlUtils;
import com.beicroon.gateway.admin.property.AuthProperty;
import com.beicroon.starter.gateway.filter.GenericAuthFilter;
import com.beicroon.starter.jwt.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AuthProperty.class)
public class AuthFilter extends GenericAuthFilter {

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Resource
    private AuthProperty authProperty;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void authenticate(ServerHttpRequest request, ServerHttpResponse response, ServerHttpRequest.Builder mutate) {
        // 判断是否需要登录
        if (this.authProperty.isInIgnoreUrls(request.getPath().value())) {
            return;
        }

        String token = request.getHeaders().getFirst(HeaderConstant.AUTHORIZATION_TOKEN);

        if (EmptyUtils.isEmpty(token)) {
            throw ExceptionUtils.authorized();
        }

        AuthUser user = JwtUtils.decode(token);

        if (!"local".equals(this.activeProfile)) {
            String cache = this.redisTemplate.opsForValue().get(String.format(JwtConstant.ADMIN_LOGIN_KEY, user.getId()));

            if (cache == null || EmptyUtils.isEmpty(cache) || !cache.equals(token)) {
                throw ExceptionUtils.authorized();
            }
        }

        mutate.header(HeaderConstant.AUTHORIZATION_USER, UrlUtils.encode(JsonUtils.toJson(user)));
    }

}
