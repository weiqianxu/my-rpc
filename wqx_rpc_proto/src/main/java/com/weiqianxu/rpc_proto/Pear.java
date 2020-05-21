package com.weiqianxu.rpc_proto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @descript：表示网络传输的一个端点（地址）
 * @Author: WeiQianXu
 * @Date: 2020/5/16 16:35
 */
@Data
@AllArgsConstructor
public class Pear {
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
}
