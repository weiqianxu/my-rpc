package com.weiqianxu.rpc.service;

import com.weiqianxu.rpc.common.utils.ReflectionUtils;
import com.weiqianxu.rpc_proto.Request;

/**
 * @descript：调用具体服务的辅助类
 * @Author: WeiQianXu
 * @Date: 2020/5/20 11:29
 */
public class ServiceInvoker {

    public Object invoker(ServiceInstance serviceInstance, Request request){
        return ReflectionUtils.invoke(serviceInstance.getTarget(),
                serviceInstance.getMethod(),
                request.getParamser());
    }
}
