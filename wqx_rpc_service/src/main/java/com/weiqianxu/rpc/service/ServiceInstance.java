package com.weiqianxu.rpc.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @descript：表示一个具体服务  实例类
 * @Author: WeiQianXu
 * @Date: 2020/5/20 11:28
 */

@Data
@AllArgsConstructor
public class ServiceInstance {

    /**
     * 服务由什么对象提供
     */
    private Object target;

    /**
     * 对象方法
     */
    private Method method;
}
