package com.weiqianxu.rpc.service;

import com.weiqianxu.rpc_proto.Request;
import com.weiqianxu.rpc_proto.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @descript：管理rpc暴露的服务
 * @Author: WeiQianXu
 * @Date: 2020/5/20 11:28
 */

@Slf4j
public class ServiceManager {

    //使用hashmap保存注册的数据
    private Map<ServiceDescriptor, ServiceInstance> service;

    public ServiceManager() {
        this.service = new ConcurrentHashMap<ServiceDescriptor, ServiceInstance>();
    }

    /**
     * 将class中的方法都当成服务注册到ServiceManager里面
     * @param clazz
     * @param bean
     * @param <T>
     */
    public <T> void register(Class<T> clazz, T bean) {

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            ServiceInstance serviceInstance = new ServiceInstance(bean,method);
            ServiceDescriptor descriptor = ServiceDescriptor.form(clazz,method);

            service.put(descriptor,serviceInstance);

            log.info("register service：{} {}", descriptor.getClazz(),descriptor.getMethod());
        }
    }

    /**
     * 查找
     * @return
     */
    public ServiceInstance lookup(Request request){
        ServiceDescriptor descriptor = request.getService();
        return service.get(descriptor);
    }
}
