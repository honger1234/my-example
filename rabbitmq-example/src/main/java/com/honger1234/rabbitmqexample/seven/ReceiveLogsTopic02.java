package com.honger1234.rabbitmqexample.seven;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * topic交换机的消费者
 */
public class ReceiveLogsTopic02 {
    private static final String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //声明 Q1 队列与绑定关系
        String queueName="Q2";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, "*.*.rabbit");
        channel.queueBind(queueName, EXCHANGE_NAME, "lazy.#");
        System.out.println("等待接收消息........... ");
        DeliverCallback deliverCallback = (consumerTag, delivery) ->{
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" 接收队列 :"+queueName+" 绑定键:"+delivery.getEnvelope().getRoutingKey()+",消息:"+message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
