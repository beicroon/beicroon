package com.beicroon.starter.web.annotation;

import com.beicroon.construct.constant.SystemConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.lang.annotation.*;

@Inherited
@Documented
@EnableDiscoveryClient
@SpringBootApplication
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MapperScans(@MapperScan(SystemConstant.MAPPER_PACKAGE))
@ComponentScans(@ComponentScan(SystemConstant.BASE_PACKAGE))
public @interface BeicroonWebBootApplication {

}

