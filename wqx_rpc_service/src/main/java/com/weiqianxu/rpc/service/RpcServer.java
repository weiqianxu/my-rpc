package com.weiqianxu.rpc.service;

import com.weiqianxu.rpc.codec.Decoder;
import com.weiqianxu.rpc.codec.Encoder;
import com.weiqianxu.rpc.codec.JSONDecoder;
import com.weiqianxu.rpc.codec.JSONEncoder;
import com.weiqianxu.rpc.common.utils.ReflectionUtils;
import com.weiqianxu.rpc.transport.HTTPTransportServer;
import com.weiqianxu.rpc.transport.RequestHandler;
import com.weiqianxu.rpc.transport.TransportServer;
import com.weiqianxu.rpc_proto.Request;
import com.weiqianxu.rpc_proto.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @descript：RPC 服务
 * @Author: WeiQianXu
 * @Date: 2020/5/20 11:29
 */

@Slf4j
public class RpcServer {

    private RpcServerConfig config;
    private TransportServer server;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;


    public RpcServer(RpcServerConfig config) {
        this.config = config;

        //net 网络模块 使用反射
        this.server = ReflectionUtils.newInstance(config.getServerClass());
        this.server.init(config.getPort(),this.handler);

        //序列化模块  使用反射
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> clazz, T bean) {
        serviceManager.register(clazz,bean);
    }

    public void start(){
        this.server.start();
    }

    public void stop(){
        this.server.stop();
    }


    /**
     * 得到初始化方法中所需要的request
     */
    private RequestHandler handler = new RequestHandler() {
        public void onRequest(InputStream inputStream, OutputStream outputStream) {

            //定义RPC返回的response
            Response response = new Response();

            try {
                //通过序列化字节获取request
                byte[] bytes = IOUtils.readFully(inputStream,inputStream.available());
                Request request = decoder.decoder(bytes,Request.class);
                log.info("get request:{}",request);

                //通过request找服务
                ServiceInstance instance = serviceManager.lookup(request);
                Object obj = serviceInvoker.invoker(instance, request);
                response.setDate(obj);

            } catch (IOException e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("RpcService got error:"
                        + e.getClass().getName()
                        + ":" + e.getMessage());
            }finally {
                try {
                    //反序列换得到字节
                    byte[] bytes = RpcServer.this.encoder.encoder(response);
                    outputStream.write(bytes);

                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }
        }
    };
}
