package com.beicroon.starter.cloud.config;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.feign.config.FeignClientConfig;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {SystemConstant.PACKAGE_BASE}, defaultConfiguration = FeignClientConfig.class)
public class CloudConfig {

}
