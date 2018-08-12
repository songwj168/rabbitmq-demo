package com.swj.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send
 * @Description: 消息生产者
 * @Author: songwj
 * @Date: 2018-08-04 18:39
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send {

    private static final String QUEUE_NAME = "queue_swj";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 从连接中获取一个通道
        Channel channel = conn.createChannel();
        // 创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "Hello World!!!";
        // 发布消息
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("Send msg: [" + msg + "]");
        // 关闭资源
        channel.close();
        conn.close();
    }

}
