package com.weiqianxu.rpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @descript：
 * @Author: WeiQianXu
 * @Date: 2020/5/18 10:29
 */
public class EncoderTest {

    @Test
    public void encoder() {

        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setName("weizaza");
        bean.setAge(18);

        byte[] bytes = encoder.encoder(bean);

        assertNotNull(bytes);
    }
}