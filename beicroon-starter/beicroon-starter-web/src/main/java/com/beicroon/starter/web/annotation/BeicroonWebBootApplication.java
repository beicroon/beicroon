package com.beicroon.starter.web.annotation;

import com.beicroon.construct.constant.SystemConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

@Inherited
@Documented
@EnableDiscoveryClient
@SpringBootApplication
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MapperScan({SystemConstant.PACKAGE_BASE})
@ComponentScan({SystemConstant.PACKAGE_BASE})
public @interface BeicroonWebBootApplication {

}

