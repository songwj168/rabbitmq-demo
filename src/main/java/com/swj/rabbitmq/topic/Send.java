package com.swj.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send
 * @Description: 生产者（主题模式）
 * @Author: songwj
 * @Date: 2018-08-08 23:10
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 发布消息
        String msg = "Hello topic";
        channel.basicPublish(EXCHANGE_NAME, "Goods.add", null, msg.getBytes());
        System.out.println("Send msg: " + msg);
        // 关闭连接
        channel.close();
        conn.close();
    }

}
