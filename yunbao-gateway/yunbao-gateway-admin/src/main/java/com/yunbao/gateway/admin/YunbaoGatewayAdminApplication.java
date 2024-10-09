package com.yunbao.gateway.admin;

import com.beicroon.construct.constant.SystemConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({SystemConstant.BASE_PACKAGE, "com.yunbao"})
public class YunbaoGatewayAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunbaoGatewayAdminApplication.class, args);
    }

}
