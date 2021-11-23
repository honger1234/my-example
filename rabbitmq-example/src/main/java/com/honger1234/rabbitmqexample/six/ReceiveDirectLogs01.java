package com.honger1234.rabbitmqexample.six;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者：direct交换机
 */
public class ReceiveDirectLogs01 {

    public static final String EXCHANGE_NAME="direct_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        /**
         * 生成一个临时的队列 队列的名称是随机的
         * 当消费者断开和该队列的连接时 队列自动删除
         */
        String queue = channel.queueDeclare().getQueue();
        //把该临时队列绑定我们的 exchange 其中 routingkey(也称之为 binding key)为空字符串
        channel.queueBind(queue,EXCHANGE_NAME,"info");
        channel.queueBind(queue,EXCHANGE_NAME,"warning");
        //成功消费的回调
        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            String message = new String(delivery.getBody(),"utf-8");
            System.out.println("ReceiveDirectLogs01控制台接收到的消息："+message);
        };
        //取消消费的回调
        CancelCallback cancelCallback=(s1)->{

        };
        System.out.println("等待消费消息......");
        channel.basicConsume(queue,true,deliverCallback,cancelCallback);
    }

}
