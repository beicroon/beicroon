package com.beicroon.construct.auth.interceptor;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.construct.entity.AuthUser;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.utils.EmptyUtils;
import com.beicroon.construct.utils.NumberUtils;
import com.beicroon.construct.utils.UrlUtils;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) throws Exception {
        // 登录用户信息
        String user = request.getHeader(HeaderConstant.AUTHORIZATION_USER);

        if (EmptyUtils.isNotEmpty(user)) {
            AuthUtils.setUser(JsonUtils.toObject(UrlUtils.decode(user), AuthUser.class));
        } else {
            String token = request.getHeader(HeaderConstant.AUTHORIZATION_TOKEN);
        }

        // 来源
        String source = request.getHeader(HeaderConstant.SOURCE);

        if (EmptyUtils.isNotEmpty(source)) {
            AuthUtils.setSource(UrlUtils.decode(source));
        }

        // 版本
        String version = request.getHeader(HeaderConstant.VERSION);

        if (EmptyUtils.isNotEmpty(version)) {
            AuthUtils.setVersion(UrlUtils.decode(version));
        }

        // 租户
        String tenantId = request.getHeader(HeaderConstant.TENANT_ID);

        if (EmptyUtils.isNotEmpty(tenantId)) {
            AuthUtils.setTenantId(NumberUtils.longOf(UrlUtils.decode(tenantId)));
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) {
        AuthUtils.clear();
    }

}
