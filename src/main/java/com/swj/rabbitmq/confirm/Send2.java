package com.swj.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send2
 * @Description: 生产者（Confirm模式, 批量发送）
 * @Author: songwj
 * @Date: 2018-08-11 13:44
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send2 {

    private static final String QUEUE_NAME = "test_queue_confirm";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 开启Confirm模式
        channel.confirmSelect();
        // 发送消息
        String msg = "Hello confirm batch msg!!!";

        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        // 消息确认
        if (channel.waitForConfirms()) {
            System.out.println("Message send success");
        } else {
            System.out.println("Message send fair");
        }

        // 关闭连接
        channel.close();
        conn.close();
    }

}
