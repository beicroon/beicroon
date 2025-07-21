package com.beicroon.starter.feign.config;

import com.beicroon.construct.auth.utils.AuthUtils;
import com.beicroon.construct.constant.HeaderConstant;
import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.json.utils.JsonUtils;
import com.beicroon.construct.utils.UrlUtils;
import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class FeignClientConfig {

    private static final List<String> HEADERS = Arrays.asList(
            HeaderConstant.SOURCE,
            HeaderConstant.VERSION,
            HeaderConstant.TRACE_ID,
            HeaderConstant.TENANT_ID,
            HeaderConstant.AUTHORIZATION_USER,
            HeaderConstant.AUTHORIZATION_TOKEN
    );

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();

                for (String header : HEADERS) {
                    template.header(header, request.getHeader(header));
                }
            } else {
                template.header(HeaderConstant.SOURCE, UrlUtils.encode(AuthUtils.getSource()));
                template.header(HeaderConstant.VERSION, UrlUtils.encode(AuthUtils.getVersion()));
                template.header(HeaderConstant.TRACE_ID, UrlUtils.encode(MDC.get(SystemConstant.TRACE_ID)));
                template.header(HeaderConstant.TENANT_ID, UrlUtils.encode(String.valueOf(AuthUtils.getTenantId())));
                template.header(HeaderConstant.AUTHORIZATION_USER, UrlUtils.encode(JsonUtils.toJson(AuthUtils.getUser())));
            }
        };
    }

}
