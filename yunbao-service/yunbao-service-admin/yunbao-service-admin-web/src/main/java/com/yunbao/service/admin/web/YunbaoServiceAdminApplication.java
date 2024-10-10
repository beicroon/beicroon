package com.yunbao.service.admin.web;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.web.admin.annotation.BeicroonWebAdminBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@BeicroonWebAdminBootApplication
@MapperScan({SystemConstant.MAPPER_PACKAGE})
@ComponentScan({SystemConstant.BASE_PACKAGE, "com.yunbao"})
public class YunbaoServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunbaoServiceAdminApplication.class, args);
    }

}
