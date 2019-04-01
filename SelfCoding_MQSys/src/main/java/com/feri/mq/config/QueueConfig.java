package com.feri.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@Author feri
 *@Date Created in 2019/4/1 12:04
 */
@Configuration
public class QueueConfig {
    @Bean
    public Queue createQueue(){
        return new Queue("orders");
    }
    @Bean
    public Queue createQueue1(){
        return new Queue("goods");
    }
}
