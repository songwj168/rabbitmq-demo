package com.swj.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send
 * @Description: 消息生产者，模拟一个生产者对应多个消费者
 * @Author: songwj
 * @Date: 2018-08-04 23:12
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发布消息
        for (int i = 0; i < 50; i++) {
            String msg = "Hello " + (i + 1);
            System.out.println("Send msg: [" + msg + "]");
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            Thread.sleep(200);
        }

        // 关闭连接
        channel.close();
        conn.close();
    }

}
