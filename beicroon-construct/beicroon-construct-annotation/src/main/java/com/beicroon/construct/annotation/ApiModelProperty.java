package com.beicroon.construct.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiModelProperty {

    String name() default "";

    boolean hidden() default false;

}
