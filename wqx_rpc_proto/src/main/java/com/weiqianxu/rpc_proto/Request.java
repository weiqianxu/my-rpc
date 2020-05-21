package com.weiqianxu.rpc_proto;

import lombok.Data;

/**
 * @descript：表示RPC的一个请求类
 * @Author: WeiQianXu
 * @Date: 2020/5/16 16:39
 */
@Data
public class Request {

    /**
     * 请求的数据
     */
    private ServiceDescriptor service;

    /**
     * 请求的参数
     */
    private Object[] paramser;
}
