package com.beicroon.service.address.web;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.web.annotation.BeicroonWebBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@BeicroonWebBootApplication
@MapperScan({SystemConstant.PACKAGE_MAPPER})
@ComponentScan({SystemConstant.PACKAGE_BASE})
public class BeicroonServiceAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeicroonServiceAddressApplication.class, args);
    }

}
