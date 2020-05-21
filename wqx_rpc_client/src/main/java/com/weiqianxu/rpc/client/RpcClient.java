package com.weiqianxu.rpc.client;

import com.weiqianxu.rpc.codec.Decoder;
import com.weiqianxu.rpc.codec.Encoder;
import com.weiqianxu.rpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @descript：Rpc客户端
 * @Author: WeiQianXu
 * @Date: 2020/5/21 11:16
 */
public class RpcClient {

    private RpcClientConfig config;

    private Encoder encoder;

    private Decoder decoder;

    /**
     * 路由选择策略
     */
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;

        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());

        //创建路由选择并初始化
        this.selector = ReflectionUtils.newInstance(this.config.getTransSelectorClass());
        this.selector.init(this.config.getPearList(),this.config.getConnection(),this.config.getTransClientClass());

    }

    /**
     * 获取接口的代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> clazz){

        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,selector)
        );
    }
}
