package com.weiqianxu.rpc.client;

import com.weiqianxu.rpc.codec.Decoder;
import com.weiqianxu.rpc.codec.Encoder;
import com.weiqianxu.rpc.transport.TransportClient;
import com.weiqianxu.rpc_proto.Request;
import com.weiqianxu.rpc_proto.Response;
import com.weiqianxu.rpc_proto.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @descript：调用远程服务的代理类
 * @Author: WeiQianXu
 * @Date: 2020/5/21 11:19
 */

@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;

    private Encoder encoder;

    private Decoder decoder;

    /**
     * 路由选择策略
     */
    private TransportSelector selector;


    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    /**
     * 请求流程：
     * 1、创建request对象
     * 2、通过网络传输调用远程服务，拿到response
     * 3、判断是否成功，成功返回date，否则抛出异常
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        //request
        Request request = new Request();
        request.setService(ServiceDescriptor.form(clazz, method));
        request.setParamser(args);

        //response
        Response response = invokeRemote(request);
        if (response == null || response.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + response);
        }

        return response.getDate();
    }

    /**
     * 网络传输
     *
     * @param request
     * @return
     */
    public Response invokeRemote(Request request) {

        Response response = null;
        TransportClient client = null;
        try {
            //通过路由选择器选择一个client出来
            client = selector.select();

            //将request通过序列化成byte数组
            byte[] bytes = encoder.encoder(request);
            InputStream inputStream = client.write(new ByteArrayInputStream(bytes));
            byte[] inBytes = IOUtils.readFully(inputStream, inputStream.available());

            //反序列化
            response = decoder.decoder(inBytes, Response.class);

        } catch (IOException e) {

            log.warn(e.getMessage(),e);

            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error:" + e.getClass().getName() + " : " + e.getMessage());

        } finally {
            if (client != null) {
                selector.relese(client);
            }
        }
        return response;
    }
}