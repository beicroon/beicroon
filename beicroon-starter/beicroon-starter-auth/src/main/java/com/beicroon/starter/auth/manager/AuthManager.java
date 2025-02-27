package com.beicroon.starter.auth.manager;

import com.beicroon.construct.auth.property.AuthProperty;
import com.beicroon.construct.auth.utils.JwtUtils;
import com.beicroon.construct.base.entity.AuthUser;
import com.beicroon.construct.cache.template.CacheTemplate;
import com.beicroon.construct.constant.JwtConstant;
import com.beicroon.construct.exception.utils.ExceptionUtils;
import com.beicroon.construct.utils.EmptyUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(AuthProperty.class)
public class AuthManager {

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Resource
    private AuthProperty authProperty;

    @Lazy
    @Resource
    private CacheTemplate cacheTemplate;

    public boolean isInIgnoreUrls(String method, String url) {
        return "OPTIONS".equals(method) || this.authProperty.isInIgnoreUrls(url);
    }

    public AuthUser getAuthUser(String token) {
        if (EmptyUtils.isEmpty(token)) {
            throw ExceptionUtils.authorized();
        }

        AuthUser user = JwtUtils.decode(token);

        if ("local".equals(this.activeProfile)) {
            return user;
        }

        String cache = this.cacheTemplate.get(String.format(JwtConstant.ADMIN_LOGIN_KEY, user.getId()));

        if (cache == null || EmptyUtils.isEmpty(cache) || !cache.equals(token)) {
            throw ExceptionUtils.authorized();
        }

        return user;
    }

    public void setCache(Long userId, String token) {
        this.cacheTemplate.set(String.format(JwtConstant.ADMIN_LOGIN_KEY, userId), token);
    }

    public void deleteCache(Long userId) {
        this.cacheTemplate.delete(String.format(JwtConstant.ADMIN_LOGIN_KEY, userId));
    }

}
