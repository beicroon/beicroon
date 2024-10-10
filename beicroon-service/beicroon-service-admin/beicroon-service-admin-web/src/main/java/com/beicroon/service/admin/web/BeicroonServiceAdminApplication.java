package com.beicroon.service.admin.web;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.web.admin.annotation.BeicroonWebAdminBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@BeicroonWebAdminBootApplication
@MapperScan({SystemConstant.PACKAGE_MAPPER})
@ComponentScan({SystemConstant.PACKAGE_BASE})
public class BeicroonServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeicroonServiceAdminApplication.class, args);
    }

}
