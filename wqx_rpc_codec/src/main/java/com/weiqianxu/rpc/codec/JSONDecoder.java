package com.weiqianxu.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @descript：基于JSON的反序列的实现
 * @Author: WeiQianXu
 * @Date: 2020/5/18 10:27
 */
public class JSONDecoder implements Decoder{
    public <T> T decoder(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
