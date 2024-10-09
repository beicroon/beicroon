package com.yunbao.service.oms.web;

import com.beicroon.starter.web.annotation.BeicroonWebBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@BeicroonWebBootApplication
@ComponentScan("com.yunbao")
public class YunbaoServiceOmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunbaoServiceOmsApplication.class, args);
    }

}
