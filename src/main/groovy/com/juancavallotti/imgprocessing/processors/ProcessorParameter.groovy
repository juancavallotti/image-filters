package com.juancavallotti.imgprocessing.processors

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Created by juancavallotti on 3/6/17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ProcessorParameter {

    String maxValue() default "";
    String minValue() default "";
    String name();

}