package com.honger1234.rabbitmqexample.eight;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;

/**
 * 生产者:测试死信队列的消费者
 */
public class Consumer02 {
    public static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();

        System.out.println("Consumer02等待接收消息");
        DeliverCallback deliverCallback = (consumerTag, delivery) ->
        {String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Consumer02 接收到消息"+message);
        };
        channel.basicConsume(DEAD_QUEUE,false,deliverCallback,consumerTag -> {});
    }
}
