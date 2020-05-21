package com.weiqianxu.rpc.service;

import com.weiqianxu.rpc.codec.Decoder;
import com.weiqianxu.rpc.codec.Encoder;
import com.weiqianxu.rpc.codec.JSONDecoder;
import com.weiqianxu.rpc.codec.JSONEncoder;
import com.weiqianxu.rpc.transport.HTTPTransportServer;
import com.weiqianxu.rpc.transport.TransportServer;
import lombok.Data;

/**
 * @descript：server配置
 * @Author: WeiQianXu
 * @Date: 2020/5/20 11:27
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> serverClass = HTTPTransportServer.class;

    /**
     * 序列化
     */
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    /**
     * 反序列化
     */
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private int port = 3000;
}
