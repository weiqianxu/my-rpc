package com.weiqianxu.rpc.client;

import com.weiqianxu.rpc.common.utils.ReflectionUtils;
import com.weiqianxu.rpc.transport.TransportClient;
import com.weiqianxu.rpc_proto.Pear;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @descript：随机选择一个网络端点 为了防止并发需要在每个方法中加锁synchronized
 * @Author: WeiQianXu
 * @Date: 2020/5/21 10:31
 */

@Slf4j
public class RandomTransportSelector implements TransportSelector{

    //已经连接好的client
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<TransportClient>();
    }

    public synchronized void init(List<Pear> pears, int counts, Class<? extends TransportClient> clazz) {

        //保证counts>=1
        counts = Math.max(counts,1);

        for (Pear pear : pears){
            for (int i = 0; i < counts; i++) {
                //连接服务
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(pear);
                clients.add(client);
            }
            log.info("connect server:{}", pear);
        }

    }

    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    public synchronized void relese(TransportClient select) {
        clients.add(select);

    }

    public synchronized void close() {

        for (TransportClient client : clients){
            client.close();
        }
        clients.clear();
    }
}
