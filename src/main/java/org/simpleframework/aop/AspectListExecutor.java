package org.simpleframework.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/6 20:04
 */
public class AspectListExecutor implements MethodInterceptor {

    private Class<?> targetClass;
    @Getter
    private List<AspectInfo> sortedAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectInfoList) {
        this.targetClass = targetClass;
        this.sortedAspectInfoList = sortAspectInfoList(aspectInfoList);
    }

    /**
     * 按照order的值进行升序排序
     * @param aspectInfoList
     * @return
     */
    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectInfoList) {
        Collections.sort(aspectInfoList, (o1, o2) -> o1.getOrderIndex() - o2.getOrderIndex());
        return aspectInfoList;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnValue = null;
        collectAccurateMatchedAspectList(method);
        if(ValidationUtil.isEmpty(sortedAspectInfoList)){
            returnValue = methodProxy.invokeSuper(proxy, args);
            return returnValue;
        }
        invokeBeforeAdvices(method, args);
        try{
            returnValue = methodProxy.invokeSuper(proxy, args);
            returnValue = invokeAfterReturningAdvices(method, args, returnValue);
        }catch (Exception e){
            invokeAfterThrowing(method, args, e);
        }

        return returnValue;
    }

    private void collectAccurateMatchedAspectList(Method method) {
        if(ValidationUtil.isEmpty(sortedAspectInfoList)){
            return;
        }
        Iterator<AspectInfo> it = sortedAspectInfoList.iterator();
        while (it.hasNext()){
            AspectInfo aspectInfo = it.next();
            if(!aspectInfo.getPointcutLocator().accurateMatches(method)){
                it.remove();
            }
        }
    }

    private void invokeAfterThrowing(Method method, Object[] args, Exception e) throws Throwable {

        for(int i = sortedAspectInfoList.size() - 1; i >= 0; i --){
            sortedAspectInfoList.get(i).getAspectObject().afterThrowing(targetClass, method, args, e);
        }
    }

    private Object invokeAfterReturningAdvices(Method method, Object[] args, Object returnValue) throws Throwable {
        Object result = null;
        for(int i = sortedAspectInfoList.size() - 1; i >= 0; i --){
            result = sortedAspectInfoList.get(i).getAspectObject().afterReturning(targetClass, method, args, returnValue);
        }

        return result;
    }

    private void invokeBeforeAdvices(Method method, Object[] args) throws Throwable {

        for (AspectInfo aspectInfo : sortedAspectInfoList) {
            aspectInfo.getAspectObject().before(targetClass, method, args);
        }
    }
}
