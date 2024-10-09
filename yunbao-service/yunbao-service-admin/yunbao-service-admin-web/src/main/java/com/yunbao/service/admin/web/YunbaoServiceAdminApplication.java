package com.yunbao.service.admin.web;

import com.beicroon.starter.web.admin.annotation.BeicroonWebAdminBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.yunbao")
@BeicroonWebAdminBootApplication
public class YunbaoServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunbaoServiceAdminApplication.class, args);
    }

}
