package com.honger1234.rabbitmqexample.three;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

/**
 *  工作队列：消息应答，应答失败将消息重新放进队列
 */
public class Task02 {
    //队列名称
    public static final String QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws Exception {
        //获取信道
        Channel channel = RabbitMqUtils.getChannel();
        /**
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.是否自动删除 最后一个消费者短开连接以后 该队列是否自动删除 true 自动删除
         * 4.该队列是否只供一个消费者进行消费 是否进行共享 true 可以多个消费者消费
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("等待发送消息");
        //从控制台发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            /**
             * 发送一个消息
             * 1.发送到那个交换机
             * 2.路由的 key 是哪个
             * 3.其他的参数信息
             * 4.发送消息的消息体
             */
            channel.basicPublish("",QUEUE_NAME,MessageProperties.PERSISTENT_TEXT_PLAIN,next.getBytes("UTF-8"));
            System.out.println("消息发送完毕");
        }

    }
}
