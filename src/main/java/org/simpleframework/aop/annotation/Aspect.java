package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/6 19:22
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    String pointcut();
}
