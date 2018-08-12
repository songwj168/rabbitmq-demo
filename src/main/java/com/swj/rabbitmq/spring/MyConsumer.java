package com.swj.rabbitmq.spring;

/**
 * @Project: rabbitmq-demo
 * @Title: MyConsumer
 * @Description: 消费者
 * @Author: songwj
 * @Date: 2018-08-12 14:05
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class MyConsumer {

    /**
     * 具体执行业务方法
     * @param msg 接收到的消息
     */
    public void listen(String msg) {
        System.out.println("Recv msg:" + msg);
    }

}
