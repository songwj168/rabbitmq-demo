package com.swj.rabbitmq.confirm;

import com.rabbitmq.client.*;
import com.swj.rabbitmq.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Project: rabbitmq-demo
 * @Title: Recv
 * @Description: 消费者
 * @Author: songwj
 * @Date: 2018-08-11 13:51
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Recv {

    private static final String QUEUE_NAME = "test_queue_confirm3";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 接收消息
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, "utf-8"));
            }
        });
    }

}
