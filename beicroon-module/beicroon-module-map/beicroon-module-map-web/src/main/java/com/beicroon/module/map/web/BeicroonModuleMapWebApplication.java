package com.beicroon.module.map.web;

import com.beicroon.construct.constant.SystemConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {SystemConstant.PACKAGE_MAPPER})
@ComponentScan(basePackages = {SystemConstant.PACKAGE_BASE})
public class BeicroonModuleMapWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeicroonModuleMapWebApplication.class, args);
    }

}
