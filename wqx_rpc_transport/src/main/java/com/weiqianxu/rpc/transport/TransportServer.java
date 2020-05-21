package com.weiqianxu.rpc.transport;

/**
 * @descript：网络传输服务端接口
 * 1、启动、监听接口
 * 2、接受请求
 * 3、关闭监听
 * @Author: WeiQianXu
 * @Date: 2020/5/18 11:28
 */
public interface TransportServer {

    void init(int port,RequestHandler handler);

    void start();

    void stop();
}
