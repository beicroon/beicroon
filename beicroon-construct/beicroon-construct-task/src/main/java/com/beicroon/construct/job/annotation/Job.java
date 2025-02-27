package com.beicroon.construct.job.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Job {

    String value();

    String init() default "";

    String destroy() default "";

}
