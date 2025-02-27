package com.beicroon.construct.database.annotation;

import com.beicroon.construct.database.enums.ScopeTypeEnums;

import java.lang.annotation.*;

@Inherited
@Documented
@Repeatable(DataPermission.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataScope {

    ScopeTypeEnums value();

    String filed() default "";

}