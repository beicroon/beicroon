package com.beicroon.starter.web.admin.annotation;

import com.beicroon.starter.web.admin.constant.BeicroonWebAdminConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeicroonWebAdminApplication {

    @AliasFor(annotation = MapperScan.class, attribute = "basePackages")
    String[] mapperPackages() default {BeicroonWebAdminConstant.MAPPER_PACKAGE};

}
