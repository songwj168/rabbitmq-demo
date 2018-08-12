package com.swj.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Project: rabbitmq-demo
 * @Title: SpringMain
 * @Description: 执行入口
 * @Author: songwj
 * @Date: 2018-08-12 14:15
 * @Company: hwjz
 * @Copyright: Copyright (c) 2017 Hwjz. All Rights Reserved.
 * @Version v1.0
 */
public class SpringMain {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
        RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean("rabbitTemplate");
        // 发送消息
        rabbitTemplate.convertAndSend("Hello world!!!");
        // 休眠1秒
        Thread.sleep(1000);
        // 销毁容器
        ctx.destroy();
    }

}
