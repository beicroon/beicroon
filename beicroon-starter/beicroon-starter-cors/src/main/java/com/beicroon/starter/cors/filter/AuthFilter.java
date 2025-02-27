package com.beicroon.starter.cors.filter;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.starter.auth.manager.AuthManager;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthFilter implements HandlerInterceptor {

    @Resource
    private AuthManager authManager;

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
        if (this.authManager.isInIgnoreUrls(request.getMethod(), request.getRequestURI())) {
            return true;
        }

        String token = request.getHeader(HeaderConstant.AUTHORIZATION_TOKEN);

        AuthUtils.setUser(this.authManager.getAuthUser(token));

        return true;
    }

}
