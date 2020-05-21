package com.weiqianxu.rpc.client;

import com.weiqianxu.rpc.codec.Decoder;
import com.weiqianxu.rpc.codec.Encoder;
import com.weiqianxu.rpc.codec.JSONDecoder;
import com.weiqianxu.rpc.codec.JSONEncoder;
import com.weiqianxu.rpc.transport.HTTPTransportClient;
import com.weiqianxu.rpc.transport.TransportClient;
import com.weiqianxu.rpc_proto.Pear;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @descript：client配置类
 * @Author: WeiQianXu
 * @Date: 2020/5/21 11:12
 */

@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> transClientClass = HTTPTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;

    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> transSelectorClass = RandomTransportSelector.class;

    private int connection = 1;

    private List<Pear> pearList = Arrays.asList(new Pear("127.0.0.1",3000));


}
