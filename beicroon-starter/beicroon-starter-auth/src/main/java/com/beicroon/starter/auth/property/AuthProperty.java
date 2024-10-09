package com.beicroon.starter.auth.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Data
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
