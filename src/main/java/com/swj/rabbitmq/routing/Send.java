package com.swj.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send
 * @Description: 生产者（路由模式）
 * @Author: songwj
 * @Date: 2018-08-07 22:30
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_routing";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明交换机，为处理路由键
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 发布消息
        String msg = "Hello direct";
        System.out.println("Send msg: " + msg);
        String routingKey = "error";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
        // 关闭连接
        channel.close();
        conn.close();
    }

}
