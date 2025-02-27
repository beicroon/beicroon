package com.beicroon.starter.cloud.config;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.construct.http.utils.HttpUtils;
import com.beicroon.starter.feign.config.FeignClientConfig;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients(basePackages = {SystemConstant.PACKAGE_BASE}, defaultConfiguration = FeignClientConfig.class)
public class CloudConfig {

    @Bean
    public RestTemplate restTemplate() {
        return HttpUtils.getTemplate();
    }

}
