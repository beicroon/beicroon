package com.beicroon.gateway.admin.filter;

import com.beicroon.construct.base.entity.AuthUser;
import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.utils.UrlUtils;
import com.beicroon.starter.auth.manager.AuthManager;
import com.beicroon.starter.gateway.filter.GenericAuthFilter;
import jakarta.annotation.Resource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends GenericAuthFilter {

    @Resource
    private AuthManager authManager;

    @Override
    protected void authenticate(ServerHttpRequest request, ServerHttpResponse response, ServerHttpRequest.Builder mutate) {
        if (this.authManager.isInIgnoreUrls(request.getMethod().name(), request.getPath().value())) {
            return;
        }

        String token = request.getHeaders().getFirst(HeaderConstant.AUTHORIZATION_TOKEN);

        AuthUser user = this.authManager.getAuthUser(token);

        mutate.header(HeaderConstant.AUTHORIZATION_USER, UrlUtils.encode(JsonUtils.toJson(user)));
    }

}
