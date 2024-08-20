package com.beicroon.service.admin.web;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.web.admin.constant.BeicroonWebAdminConstant;
import com.beicroon.starter.web.annotation.BeicroonBootApplication;
import org.springframework.boot.SpringApplication;

@BeicroonBootApplication(mapperPackages = {SystemConstant.MAPPER_PACKAGE, BeicroonWebAdminConstant.MAPPER_PACKAGE})
public class BeicroonServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeicroonServiceAdminApplication.class, args);
    }

}
