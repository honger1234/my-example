package com.honger1234.rabbitmqexample.three;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.honger1234.rabbitmqexample.utils.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 工作队列的消费者，添加手动应答模式
 */
public class WorkConsumer1 {
    public static final String QUEUE_NAME="ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("c1等待消费");
        //如何消费消息的回调
        DeliverCallback deliverCallback=(consumerTag, delivery) -> {
            SleepUtils.sleep(1);
           String message = new String(delivery.getBody());
            System.out.println("接收到的消息:" + message);
            /**
             * 1.消息标记 tag
             * 2.是否批量应答未应答消息
             */
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        };
        //是否自动应答
        boolean autoAck=false;
        channel.basicConsume(QUEUE_NAME,autoAck,deliverCallback,cancelCallback);
    }
}
