package com.beicroon.starter.web.admin.annotation;

import com.beicroon.starter.web.annotation.BeicroonWebBootApplication;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@BeicroonWebBootApplication
@Retention(RetentionPolicy.RUNTIME)
public @interface BeicroonWebAdminBootApplication {

}
