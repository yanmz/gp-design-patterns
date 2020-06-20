package com.example.demo.config;

import java.lang.annotation.*;

/**
 * @author cuishuoguo
 * Created on 2019/11/11.
 */
@Inherited
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiType {

    String value() default "";
}
