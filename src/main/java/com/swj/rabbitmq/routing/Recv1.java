package com.swj.rabbitmq.routing;

import com.rabbitmq.client.*;
import com.swj.rabbitmq.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Project: rabbitmq-demo
 * @Title: Recv1
 * @Description: 消费者1（路由模式）
 * @Author: songwj
 * @Date: 2018-08-07 22:41
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Recv1 {

    private static final String EXCHANGE_NAME = "test_exchange_routing";
    private static final String QUEUE_NAME = "test_queue_routing_level1";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        final Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "info");
        // 一次只处理一个
        channel.basicQos(1);
        // 创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[1] Recv msg: " + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[1] Done");
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
