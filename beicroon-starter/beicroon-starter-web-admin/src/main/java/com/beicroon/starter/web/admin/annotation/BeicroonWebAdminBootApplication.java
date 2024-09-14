package com.beicroon.starter.web.admin.annotation;

import com.beicroon.construct.constant.SystemConstant;
import com.beicroon.starter.web.admin.constant.BeicroonWebAdminConstant;
import com.beicroon.starter.web.annotation.BeicroonWebBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@BeicroonWebBootApplication
@Retention(RetentionPolicy.RUNTIME)
public @interface BeicroonWebAdminBootApplication {

    @AliasFor(annotation = MapperScan.class, attribute = "basePackages")
    String[] mapperPackages() default {SystemConstant.MAPPER_PACKAGE, BeicroonWebAdminConstant.MAPPER_PACKAGE};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] componentPackages() default {SystemConstant.BASE_PACKAGE};

}
