package com.beicroon.construct.annotation;


import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface NeedValue {

    String message() default "不能为空";

    Class<?>[] groups() default {};

}
