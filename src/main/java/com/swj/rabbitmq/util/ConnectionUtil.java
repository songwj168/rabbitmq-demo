package com.swj.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Project: rabbitmq-demo
 * @Title: ConnectionUtil
 * @Description: RabbitMQ连接获取工具类
 * @Author: songwj
 * @Date: 2018-08-04 18:26
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class ConnectionUtil {

    /**
     * 获取RabbitMQ连接
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;

        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();

            // 设置连接的主机IP
            connectionFactory.setHost("127.0.0.1");
            // 设置连接的端口
            connectionFactory.setPort(5672);
            // 设置连接的虚拟主机名
            connectionFactory.setVirtualHost("/vhost_swj");
            // 设置连接的用户名和密码
            connectionFactory.setUsername("swj");
            connectionFactory.setPassword("123456");

            conn = connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

}
