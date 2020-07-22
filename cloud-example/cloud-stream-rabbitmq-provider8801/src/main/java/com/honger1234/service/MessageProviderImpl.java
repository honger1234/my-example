package com.honger1234.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@EnableBinding(Source.class)//定义消息推送通道
public class MessageProviderImpl implements IMessageProvider{
    
    @Autowired
    private MessageChannel output;//消息发送通道

    public String send() {
        String s= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(s).build());
        System.out.println("******s:"+s);
        return null;
    }
}
