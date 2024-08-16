package com.beicroon.construct.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldSearch {

    String name();

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
