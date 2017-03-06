package com.juancavallotti.imgprocessing.processors

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Created by juancavallotti on 2/21/15.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface DisplaySettings {

    String displayText()

}