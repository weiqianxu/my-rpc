package com.weiqianxu.rpc.example;

/**
 * @descript：
 * @Author: WeiQianXu
 * @Date: 2020/5/21 13:52
 */
public class CalcServiceImpl  implements CalcService{


    public int add(int a, int b) {
        return a+b;
    }

    public int minus(int a, int b) {
        return a-b;
    }
}
