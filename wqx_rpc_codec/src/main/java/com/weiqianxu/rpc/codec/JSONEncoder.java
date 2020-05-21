package com.weiqianxu.rpc.codec;


import com.alibaba.fastjson.JSON;

/**
 * @descript：基于JSON的序列化实现
 * @Author: WeiQianXu
 * @Date: 2020/5/18 10:18
 */
public class JSONEncoder implements Encoder {
    public byte[] encoder(Object object) {
        return JSON.toJSONBytes(object);
    }
}
