package com.beicroon.construct.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldSearch {

    String value();

    boolean ne() default false;

    boolean in() default false;

    boolean like() default false;

    boolean likeLeft() default false;

    boolean likeRight() default false;

    boolean gt() default false;

    boolean ge() default false;

    boolean lt() default false;

    boolean le() default false;

    boolean between() default false;

}
