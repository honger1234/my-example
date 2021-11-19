package com.honger1234.rabbitmqexample.two;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 工作线程消费者
 */
public class WorkConsumer2 {
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("等待接收消息");
        //推送的消息如何进行消费的回调
        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            String s = new String(delivery.getBody());
            System.out.println(s);
        };
        //取消消费的一个回调接口，如在消费的时候队列被删除
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println("消息消费被中断");
        };
        System.out.println("工作线程c2");
        //消费消息
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);
    }
}
