package com.honger1234.rabbitmqexample.consumer;


//@Component
//@Slf4j
//public class DeadLetterQueueConsumer {
//
//    @RabbitListener(queues = "QD")
//    public void receiveQD(Message message, Channel channel) throws UnsupportedEncodingException {
//        String s = new String(message.getBody(), "utf-8");
//        log.info("当前时间：{},接收到的消息:{}",new Date().toString(),s);
//    }
//}

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class DeadLetterQueueConsumer {
    @RabbitListener(queues = "QD")
    public void receiveD(Message message, Channel channel) throws IOException
    {String msg = new String(message.getBody());
        log.info("当前时间：{},收到死信队列信息{}", new Date().toString(), msg);
    }
}
