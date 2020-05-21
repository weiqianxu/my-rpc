package com.weiqianxu.rpc.client;

import com.weiqianxu.rpc.transport.TransportClient;
import com.weiqianxu.rpc_proto.Pear;

import java.util.List;

/**
 * @descript：表示选择哪个server连接  路由选择策略
 * @Author: WeiQianXu
 * @Date: 2020/5/21 10:25
 */
public interface TransportSelector {

    /**
     * 初始化selector
     * @param pears 可选择的server端点信息
     * @param counts server与client建立多少个连接
     * @param client client实现class
     */
    void init(List<Pear> pears,
              int counts,
              Class<? extends TransportClient> client);

    /**
     * 选择一个Transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     */
    void relese(TransportClient select);

    /**
     * 关闭连接
     */
    void close();


}
