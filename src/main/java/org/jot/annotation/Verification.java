package org.jot.annotation;

import java.lang.annotation.*;

/**
 * @Author qjj
 * @Date 2020-12-23 10:43
 * @Version 1.0
 * @Class Log.java
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Verification {

    boolean required() default true;

}
