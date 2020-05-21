package com.weiqianxu.rpc_proto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @descript：表示RPC的返回类
 * @Author: WeiQianXu
 * @Date: 2020/5/16 16:41
 */
@Data
public class Response {

    /**
     * 返回编码（0-成功，非0表失败）
     */
    private int code = 0;
    /**
     * 返回的具体错误信息
     */
    private String message = "ok";
    /**
     * 返回数据
     */
    private Object date;
}
