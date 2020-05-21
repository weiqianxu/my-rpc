package com.weiqianxu.rpc.example;

import com.weiqianxu.rpc.client.RpcClient;

/**
 * @descript：
 * @Author: WeiQianXu
 * @Date: 2020/5/21 13:50
 */
public class Client {

    public static void main(String[] args){

        RpcClient rpcClient = new RpcClient();
        CalcService calcService = rpcClient.getProxy(CalcService.class);


        int add = calcService.add(1, 4);

        int minus = calcService.minus(10, 8);

        System.out.println("=======add=======" + add);
        System.out.println("=======minus=======" + minus);
    }
}
