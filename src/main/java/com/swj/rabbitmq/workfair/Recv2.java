package com.swj.rabbitmq.workfair;

import com.rabbitmq.client.*;
import com.swj.rabbitmq.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Project: rabbitmq-demo
 * @Title: Recv2
 * @Description: 消费者2
 * @Author: songwj
 * @Date: 2018-08-05 14:07
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Recv2 {

    private static final String QUEUE_NAME = "test_fair_queue";

    public static void main(String[] args) throws IOException {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        final Channel channel = conn.createChannel();
        // 声明队列
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        // 保证一次只分发一个
        channel.basicQos(1);

        // 创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[2] Recv msg: [" + msg + "]");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[2] Done");
                    // 手动回执
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        // 关闭自动应答
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

}
