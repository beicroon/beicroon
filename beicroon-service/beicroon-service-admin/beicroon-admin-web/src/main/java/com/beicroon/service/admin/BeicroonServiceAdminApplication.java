package com.beicroon.service.admin;

import com.beicroon.construct.constant.SystemConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(SystemConstant.BASE_PACKAGE)
public class BeicroonServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeicroonServiceAdminApplication.class, args);
    }

}
