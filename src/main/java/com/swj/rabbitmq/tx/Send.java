package com.swj.rabbitmq.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.swj.rabbitmq.util.ConnectionUtil;

/**
 * @Project: rabbitmq-demo
 * @Title: Send
 * @Description: 生产者（事务机制）
 * @Author: songwj
 * @Date: 2018-08-09 23:55
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class Send {

    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建通道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        try {
            // 开启事务
            channel.txSelect();
            // 发送消息
            String msg = "hello tx";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            //int val = 1 / 0;
            System.out.println("Send msg:" + msg);
            // 提交事务
            channel.txCommit();
        } catch (Exception e) {
            // 回滚事务
            channel.txRollback();
            System.out.println("Send msg rollback");
        }
    }

}
