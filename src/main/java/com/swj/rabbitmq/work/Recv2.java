package com.swj.rabbitmq.work;

import com.rabbitmq.client.*;
import com.swj.rabbitmq.util.ConnectionUtil;

import java.io.IOException;

/**
 * @Project: rabbitmq-demo
 * @Title: Recv2
 * @Description: 消费者2
 * @Author: songwj
 * @Date: 2018-08-04 23:21
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Recv2 {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 接收到达消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("Recv 2 msg：" + msg);
                try {
                    // 休眠2秒
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
