package com.weiqianxu.rpc.codec;

/**
 * @descript：反序列化接口
 * @Author: WeiQianXu
 * @Date: 2020/5/18 10:16
 */
public interface Decoder {

    <T> T decoder(byte[] bytes,Class<T> clazz);
}
