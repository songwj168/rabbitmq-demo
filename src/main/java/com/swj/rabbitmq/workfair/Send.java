package com.swj.rabbitmq.workfair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send
 * @Description: 消息生产者（公平分发）
 * @Author: songwj
 * @Date: 2018-08-05 13:59
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send {

    private static final String QUEUE_NAME = "test_fair_queue";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        // 每个消费者一次只处理一个消息，每个消费者再收到确认消息之前，一次只发送一个消息
        channel.basicQos(1);

        // 生产消息
        for (int i = 0; i < 50; i++) {
            String msg = "[WQ] Hello" + (i + 1);
            System.out.println("Send msg: [" + msg + "]");
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            Thread.sleep(5);
        }

        // 关闭资源
        channel.close();
        conn.close();
    }

}
