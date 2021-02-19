package io.adana.infinite.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sakura
 * @version 1.1
 * @date 2021-02-19 09:41
 * @description RateLimit
 * @scope io.adana.infinite.admin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RateLimit {

    int expireTime();

    int maxCount();

    boolean toSigneIn() default true;
}
