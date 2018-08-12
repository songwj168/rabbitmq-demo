package com.swj.rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send
 * @Description: 生产者（订阅模式，一个消息对应多个消费者）
 * @Author: songwj
 * @Date: 2018-08-05 19:14
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明交换机（转发器）
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 发布消息
        String msg = "Hello world";
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        // 关闭连接
        channel.close();
        conn.close();
    }

}
