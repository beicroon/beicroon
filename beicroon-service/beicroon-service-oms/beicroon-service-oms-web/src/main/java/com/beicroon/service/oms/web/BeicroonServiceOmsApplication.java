package com.beicroon.service.oms.web;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.web.annotation.BeicroonWebBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@BeicroonWebBootApplication
@MapperScan({SystemConstant.PACKAGE_MAPPER})
@ComponentScan({SystemConstant.PACKAGE_BASE})
public class BeicroonServiceOmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeicroonServiceOmsApplication.class, args);
    }

}
