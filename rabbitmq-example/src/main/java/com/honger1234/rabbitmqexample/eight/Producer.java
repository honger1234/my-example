package com.honger1234.rabbitmqexample.eight;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

/**
 * 生产者：死信队列
 */
public class Producer {
    public static final String NORMAL_EXCHANGE="normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //设置消息TTL的时间
        AMQP.BasicProperties build = new AMQP.BasicProperties().builder().expiration("10000").build();
        System.out.println("生产者Producer开始发送消息");
        for (int i = 0; i < 10; i++) {
            String message = "info"+i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Producer生产者发送的消息:"+message);
        }
    }
}
