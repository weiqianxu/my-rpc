package com.weiqianxu.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @descript：处理网络请求是的handler
 * @Author: WeiQianXu
 * @Date: 2020/5/18 11:29
 */
public interface RequestHandler {

    void onRequest(InputStream inputStream, OutputStream outputStream);
}
