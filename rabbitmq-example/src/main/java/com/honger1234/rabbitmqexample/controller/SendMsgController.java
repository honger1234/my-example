package com.honger1234.rabbitmqexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/ttl")
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("发送的时间：{}，消息为:{}",new Date().toString(),message);
        rabbitTemplate.convertAndSend("X","XA",message);
        rabbitTemplate.convertAndSend("X","XB",message);
    }

    @GetMapping("/sendMsg/{message}/{ttl}")
    public void sendMsg(@PathVariable String message,@PathVariable String ttl){
        log.info("发送的时间：{}，消息延迟{}毫秒为:{}",new Date().toString(),ttl,message);
        rabbitTemplate.convertAndSend("X","XC",message,(msg)->{
            //设置过期时间
            msg.getMessageProperties().setExpiration(ttl);
            return msg;
        });
    }
}
