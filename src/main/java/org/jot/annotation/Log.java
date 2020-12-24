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
public @interface Log {

    /**
     * 日志简单描述
     *
     * @return
     */
    String value() default "";

    /**
     * 是否开启日志记录
     *
     * @return
     */
    boolean isOpen() default true;

    /**
     * 是否记录参数
     *
     * @return
     */
    boolean isRecordParameters() default false;

    /**
     * 是否记录结果数据
     *
     * @return
     */
    boolean isRecordResultData() default false;

}
