package com.beicroon.starter.auth.handler;

import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.construct.entity.AuthUser;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.utils.UrlUtils;
import com.beicroon.starter.auth.manager.AuthManager;
import com.beicroon.starter.auth.property.AuthProperty;
import jakarta.annotation.Resource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler {

    @Resource
    private AuthProperty authProperty;

    @Resource
    private AuthManager authManager;

    public void authenticate(ServerHttpRequest request, ServerHttpRequest.Builder mutate) {
        // 判断是否需要登录
        if (this.authProperty.isInIgnoreUrls(request.getPath().value())) {
            return;
        }

        String token = request.getHeaders().getFirst(HeaderConstant.AUTHORIZATION_TOKEN);

        AuthUser user = this.authManager.getAuthUser(token);

        mutate.header(HeaderConstant.AUTHORIZATION_USER, UrlUtils.encode(JsonUtils.toJson(user)));
    }

}
