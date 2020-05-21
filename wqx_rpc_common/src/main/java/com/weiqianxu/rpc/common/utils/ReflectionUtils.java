package com.weiqianxu.rpc.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @descript：反射的工具类
 * @Author: WeiQianXu
 * @Date: 2020/5/16 16:44
 */
public class ReflectionUtils {

    /**
     * 根据class创建对象
     * @param clazz 待创建对象的class类
     * @param <T> 返回的类型
     * @return  创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    /**
     * 获取class类的共有方法
     * @return 当前类声明的共有方法
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> methodList = new ArrayList<Method>();
        for(Method method : methods){
            if (Modifier.isPublic(method.getModifiers())){
                methodList.add(method);
            }
        }
        return methodList.toArray(new Method[0]);

    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法参数
     * @return 返回结果
     */
    public static Object invoke(Object obj,
                         Method method,
                         Object...args){

        try {
            return method.invoke(obj,args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
