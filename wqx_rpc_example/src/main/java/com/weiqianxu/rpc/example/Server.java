package com.weiqianxu.rpc.example;

import com.weiqianxu.rpc.service.RpcServer;
import com.weiqianxu.rpc.service.RpcServerConfig;

/**
 * @descript 这是服务类，需要注册和启动，运行完后在运行client类
 * @Author: WeiQianXu
 * @Date: 2020/5/21 13:51
 */
public class Server {

    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());

        //注册并启动服务
        server.register(CalcService.class,new CalcServiceImpl());
        server.start();

    }
}
