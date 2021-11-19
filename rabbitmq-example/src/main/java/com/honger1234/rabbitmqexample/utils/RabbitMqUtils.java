package com.honger1234.rabbitmqexample.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqUtils {
    //得到一个连接的 channel
    public static Channel getChannel() throws Exception {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.16.129");
        factory.setUsername("zt");
        factory.setPassword("password");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;

    }
}
