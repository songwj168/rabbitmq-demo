package com.swj.rabbitmq.simple;

import com.rabbitmq.client.*;
import com.swj.rabbitmq.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Project: rabbitmq-demo
 * @Title: RecvNew
 * @Description: 消息接收者，新写法
 * @Author: songwj
 * @Date: 2018-08-04 20:27
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class RecvNew {

    private static final String QUEUE_NAME = "queue_swj";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 接收消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("New receive msg: [" + msg + "]");
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
