package com.yunbao.service.tms.web;

import com.beicroon.starter.web.annotation.BeicroonWebBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@BeicroonWebBootApplication
@ComponentScan("com.yunbao")
public class YunbaoServiceTmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunbaoServiceTmsApplication.class, args);
    }

}
