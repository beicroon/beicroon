package com.yunbao.service.tms.web;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.web.annotation.BeicroonWebBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@BeicroonWebBootApplication
@MapperScan({SystemConstant.MAPPER_PACKAGE})
@ComponentScan({SystemConstant.BASE_PACKAGE, "com.yunbao"})
public class YunbaoServiceTmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunbaoServiceTmsApplication.class, args);
    }

}
