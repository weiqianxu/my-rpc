package com.weiqianxu.rpc.codec;

/**
 * @descript： 序列化接口
 * @Author: WeiQianXu
 * @Date: 2020/5/18 10:15
 */
public interface Encoder {

    byte[] encoder(Object object);
}
