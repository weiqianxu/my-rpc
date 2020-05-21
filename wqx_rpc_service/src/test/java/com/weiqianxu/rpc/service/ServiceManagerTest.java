package com.weiqianxu.rpc.service;

import com.weiqianxu.rpc.common.utils.ReflectionUtils;
import com.weiqianxu.rpc_proto.Request;
import com.weiqianxu.rpc_proto.ServiceDescriptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @descript：
 * @Author: WeiQianXu
 * @Date: 2020/5/20 15:59
 */
public class ServiceManagerTest {

    ServiceManager sm;

    @Before
    public void init(){
        sm = new ServiceManager();

        //在测试查找方法lookup之前需要先进行注册
        TestInstance instance = new TestClass();
        sm.register(TestInstance.class,instance);
    }

    @Test
    public void register() {

        TestInstance instance = new TestClass();

        //测试：将TestInstance中的方法注册到sm中，
        //结果：register service：com.weiqianxu.rpc.service.TestInstance hello
        sm.register(TestInstance.class,instance);
    }

    @Test
    public void lookup() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestInstance.class);
        ServiceDescriptor descriptor = ServiceDescriptor.form(TestInstance.class,methods[0]);


        Request request = new Request();
        request.setService(descriptor);


        ServiceInstance lookup = sm.lookup(request);

        assertNotNull(lookup);
    }
}