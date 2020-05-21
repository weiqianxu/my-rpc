package com.weiqianxu.rpc.transport;

import com.weiqianxu.rpc_proto.Pear;
import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;

/**
 * @descript：网络传输客户端接口
 * 1、创建链接
 * 2、发送数据，并且等待响应
 * 3、关闭连接
 * @Author: WeiQianXu
 * @Date: 2020/5/18 11:25
 */
public interface TransportClient {

    void connect(Pear pear);

    InputStream write(InputStream inputStream);

    void close();
}
