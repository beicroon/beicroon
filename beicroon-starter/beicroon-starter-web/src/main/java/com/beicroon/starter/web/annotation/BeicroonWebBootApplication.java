package com.beicroon.starter.web.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.lang.annotation.*;

@Inherited
@Documented
@EnableDiscoveryClient
@SpringBootApplication
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeicroonWebBootApplication {

}

