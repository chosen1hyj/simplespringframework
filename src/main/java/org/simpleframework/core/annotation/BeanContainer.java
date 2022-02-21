package org.simpleframework.core.annotation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/5 19:49
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BeanContainer {

    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION =
            Arrays.asList(Component.class, Controller.class, Service.class, Repository.class, Aspect.class);


    public static BeanContainer getInstance(){
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;
        ContainerHolder(){
            instance = new BeanContainer();
        }

    }

    /**
     * 容器是否已加载过bean
     */
    private boolean loaded = false;

    public boolean isLoaded(){
        return loaded;
    }

    public int size(){
        return beanMap.size();
    }



    /**
     * 扫描加载所有Bean
     * @param packageName
     */
    public synchronized void loadBeans(String packageName){

        if(isLoaded()){
            log.warn("BeanContainer has been loaded");
            return;
        }

        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if(ValidationUtil.isEmpty(classSet)){
            log.warn("extract nothing from packageName " + packageName);
            return;
        }

        for(Class<?> clazz: classSet){
            for (Class<? extends Annotation> annotation: BEAN_ANNOTATION) {
                if(clazz.isAnnotationPresent(annotation)){
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }


    /**
     *
     * @param clazz Class对象
     * @param bean Bean实例
     * @return 原有的Bean实例,没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean){
        return beanMap.put(clazz, bean);
    }

    /**
     *
     * @param clazz Class对象
     * @return 原有的Bean实例,没有则返回null
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     *
     * @return Class 集合
     */
    public Set<Class<?>> getClasses(){
        return beanMap.keySet();
    }

    /**
     *
     * @return Bean集合
     */
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    public Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotation){
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> clazz: keySet){
            if(clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }

        return classSet.size() > 0 ? classSet : null;
    }

    public Set<Class<?>> getClassBySuper(Class<?> interfaceOrClass){
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> clazz: keySet){
            if(interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }

        return classSet.size() > 0 ? classSet : null;
    }


}
