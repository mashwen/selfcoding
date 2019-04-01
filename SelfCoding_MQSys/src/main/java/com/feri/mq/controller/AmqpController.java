package com.feri.mq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/4/1 11:59
 */
@RestController
public class AmqpController {

    @Autowired
    private AmqpTemplate amqpTemplate;
    //发送消息
    @PostMapping("mq/sendmsg.do")
    public String sendMsg(String msg){
        amqpTemplate.convertAndSend("orders",msg);
        return "消息发送成功";
    }

}
