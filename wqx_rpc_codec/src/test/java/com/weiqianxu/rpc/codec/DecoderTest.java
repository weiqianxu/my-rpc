package com.weiqianxu.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @descript：
 * @Author: WeiQianXu
 * @Date: 2020/5/18 10:29
 */
public class DecoderTest {

    @Test
    public void decoder() {

        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setName("weizaza");
        bean.setAge(18);

        //得到序列化后的byte
        byte[] bytes = encoder.encoder(bean);

        Decoder decoder = new JSONDecoder();

        //将序列化后的数据进行反序列化
        TestBean bean1 = decoder.decoder(bytes,TestBean.class);

        assertEquals(bean.getName(),bean1.getName());
        assertEquals(bean.getAge(),bean1.getAge());

    }
}