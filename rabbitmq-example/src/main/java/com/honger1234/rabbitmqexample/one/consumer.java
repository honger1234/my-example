package com.honger1234.rabbitmqexample.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者：接收消息
 */
public class consumer {
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //IP
        connectionFactory.setHost("192.168.16.129");
        //用户名
        connectionFactory.setUsername("zt");
        //密码
        connectionFactory.setPassword("password");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息");
        //推送的消息如何进行消费的回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };
        //取消消费的一个回调接口，如在消费的时候队列被删除了
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println("消息消费被中断");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         */

        channel.basicConsume(QUEUE_NAME,false,deliverCallback,cancelCallback);

    }
}
