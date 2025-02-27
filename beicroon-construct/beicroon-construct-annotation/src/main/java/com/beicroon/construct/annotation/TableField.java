package com.beicroon.construct.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableField {

    String name();

    boolean exist() default true;

}
