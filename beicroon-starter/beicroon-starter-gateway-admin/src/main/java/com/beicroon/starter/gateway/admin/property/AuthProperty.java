package com.beicroon.starter.gateway.admin.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Data
@RefreshScope
@ConfigurationProperties("auth")
public class AuthProperty {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    private List<String> ignores;

    public boolean isInIgnoreUrls(String url) {
        for (String ignoreUrl : this.ignores) {
            if (AuthProperty.PATH_MATCHER.match(ignoreUrl, url)) {
                return true;
            }
        }

        return false;
    }

}
