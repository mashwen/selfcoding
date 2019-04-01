package com.feri.mq.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 *@Author feri
 *@Date Created in 2019/4/1 12:02
 */
@Service
@RabbitListener(queues = "orders")
public class MqConsumer {
    @RabbitHandler
    public void recive(String msg){
        System.err.println("消费者："+msg);
    }

}
