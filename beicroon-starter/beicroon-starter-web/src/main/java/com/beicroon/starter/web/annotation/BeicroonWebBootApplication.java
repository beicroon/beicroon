package com.beicroon.starter.web.annotation;

import com.beicroon.construct.constant.SystemConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Inherited
@Documented
@EnableDiscoveryClient
@SpringBootApplication
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MapperScan(SystemConstant.MAPPER_PACKAGE)
@ComponentScan(SystemConstant.BASE_PACKAGE)
public @interface BeicroonWebBootApplication {

    @AliasFor(annotation = MapperScan.class, attribute = "basePackages")
    String[] mapperPackages() default {SystemConstant.MAPPER_PACKAGE};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] componentPackages() default {SystemConstant.BASE_PACKAGE};

}

