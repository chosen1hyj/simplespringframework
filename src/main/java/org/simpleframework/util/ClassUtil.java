package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: Chosen1
 * @date: 2022/2/5 17:17
 */
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类集合
     * @param packageName
     * @return
     */
    public static Set<Class<?>>extractPackageClass(String packageName){

        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(packageName.replace('.', '/'));
        if(url == null){
            log.warn("unable to retrieve anything from package " + packageName);
            return null;
        }
        Set<Class<?>> classSet = null;
        if(url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        //TODO 此处可加入针对其他类型资源的处理
        return classSet;
    }

    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if(!fileSource.isDirectory()){
            return;
        }

        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()){
                    return true;
                }else{
                    String absoluteFilePath = file.getAbsolutePath();
                    if(absoluteFilePath.endsWith(".class")){
                        addToClassSet(absoluteFilePath);
                    }
                }
                return false;
            }

            private void addToClassSet(String absoluteFilePath) {
                absoluteFilePath = absoluteFilePath.replace(File.separator, ".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                Class targeClass = loadClass(className);
                emptyClassSet.add(targeClass);
            }
        });
        if(files != null){
            for(File f: files){
                extractClassFile(emptyClassSet, f, packageName);
            }
        }
    }

    /**
     * 获取Class对象
     * @param className class全名=package+类名
     * @return
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * 获取classLoader
     * @return 当前ClassLoader
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static <T> T newInstance(Class<T> clazz, boolean accessible){

        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return constructor.newInstance();
        } catch (Exception e) {
            log.error("new Instance error", e);
            throw new RuntimeException(e);
        }
    }

    public static void setField(Field field, Object target, Object value, boolean accessible){
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }

}
