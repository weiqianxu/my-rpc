package com.weiqianxu.rpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @descript：
 * @Author: WeiQianXu
 * @Date: 2020/5/18 9:37
 */
public class ReflectionUtilsTest {

    @Test
    public void newInstance() {

        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);

        //assert...表示期望值怎么样，可以不为null，可以等于多少
        assertNotNull(testClass);
    }

    @Test
    public void getPublicMethods() {

        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);
        String name = methods[0].getName();
        assertEquals("b",name);
    }

    @Test
    public void invoke() {

        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);

        Method method = methods[0];

        TestClass t = new TestClass();

        Object o = ReflectionUtils.invoke(t, method);
    }
}