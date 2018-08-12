package com.swj.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @Project: rabbitmq-demo
 * @Title: Send3
 * @Description: 生产者，Confirm异步
 * @Author: songwj
 * @Date: 2018-08-11 17:41
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send3 {

    private static final String QUEUE_NAME = "test_queue_confirm3";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 开启Confirm模式
        channel.confirmSelect();
        // 存储消息标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());

        // 为通道添加监听
        channel.addConfirmListener(new ConfirmListener() {
            // 处理应答标记
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("====handleAck multiple====");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("====handleAck multiple → false====");
                    confirmSet.remove(deliveryTag);
                }
            }

            // 处理未应答标记
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("====handlNack multiple====");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("====handleNack multiple → false====");
                    confirmSet.remove(deliveryTag);
                }
            }
        });

        // 发送消息
        String msg = "Hello confirm msg!!!";

        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            confirmSet.add(seqNo);
        }
    }

}
