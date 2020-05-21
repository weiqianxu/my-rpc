package com.weiqianxu.rpc_proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @descript：表示服务
 * @Author: WeiQianXu
 * @Date: 2020/5/16 16:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    /**
     * 类名
     */
    private String clazz;
    /**
     * 方法名
     */
    private String method;
    /**
     * 返回类型
     */
    private String returnType;
    /**
     * 参数类型，多个是数组
     */
    private String[] parameterType;


    //根据传进来的class和method生成一个ServiceDescriptor的key，在service管理器中的需要使用
    public static ServiceDescriptor form(Class clazz,Method method){

        ServiceDescriptor descriptor = new ServiceDescriptor();
        descriptor.setClazz(clazz.getName());
        descriptor.setMethod(method.getName());
        descriptor.setReturnType(method.getReturnType().getName());

        //将得到的多个class拿出来
        Class<?>[] types = method.getParameterTypes();
        String[] paramtterType = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            paramtterType[i] = types[i].getName();
        }

        descriptor.setParameterType(paramtterType);

        return descriptor;
    }


    /**
     * 重新equals  hashCode  toString方法
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) o;

        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {

        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "clazz = " + clazz
                + ",method = " + method
                + ",returnType = " + returnType
                + ",parameterType = " + Arrays.toString(parameterType);
    }
}
