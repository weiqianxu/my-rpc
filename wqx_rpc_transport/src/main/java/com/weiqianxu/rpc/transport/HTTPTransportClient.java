package com.weiqianxu.rpc.transport;

import com.weiqianxu.rpc_proto.Pear;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @descript：实现客户端网络传输
 * @Author: WeiQianXu
 * @Date: 2020/5/18 11:31
 */
public class HTTPTransportClient implements TransportClient {

    private String url;

    public void connect(Pear pear) {

        this.url = "http://" + pear.getHost() + ":" + pear.getPort();
    }

    public InputStream write(InputStream inputStream) {

        try {
            //1、连接服务器，并设置规则
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);//不缓存
            connection.setRequestMethod("POST");
            connection.connect();

            //2、把inputstream发送给server
            IOUtils.copy(inputStream,connection.getOutputStream());

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK){
                return connection.getInputStream();
            }else{
                return connection.getErrorStream();
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void close() {

    }
}
