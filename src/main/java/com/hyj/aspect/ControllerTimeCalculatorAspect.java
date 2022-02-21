package com.hyj.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: Chosen1
 * @date: 2021/10/7 14:14
 */
@Aspect(pointcut = "execution(* com.imooc.controller.frontend..*.*(..))")
@Order(0)
@Slf4j
public class ControllerTimeCalculatorAspect extends DefaultAspect {

    private long timestampCache;
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("开始计时, 执行的类是[{}], 执行的方法是[{}], 参数是[{}]", targetClass.getName(), method, args);
        timestampCache = System.currentTimeMillis();
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        long endTime = System.currentTimeMillis();
        long costTime = endTime - timestampCache;
        log.info("结束计时, 执行的类是[{}], 执行的方法是[{}], 参数是[{}], 返回值是[{}], 时间为[{}]",
                targetClass.getName(), method, args, returnValue, costTime);
        return returnValue;
    }
}
