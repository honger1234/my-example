package com.honger1234.rabbitmqexample.eight;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;

/**
 * 生产者:测试死信队列的消费者
 */
public class Consumer01 {
    public static final String NORMAL_EXCAHNGE="normal_exchange";
    public static final String DEAD_EXCHANGE="dead_exchange";
    public static final String normal_queue="normal_queue";
    public static final String DEAD_QUEUE="dead_queue";

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(NORMAL_EXCAHNGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);

        //声明队列
        channel.queueDeclare(DEAD_QUEUE,true,false,false,null);
        //正常队列绑定死信队列信息
        HashMap<String, Object> map = new HashMap<>();
        //正常队列设置死信交换机 参数 key 是固定值
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //正常队列设置死信 routing-key 参数 key 是固定值
        map.put("x-dead-letter-routing-key", "lisi");
        map.put("x-max-length", 6);
        channel.queueDeclare(normal_queue,true,false,false,map);

        //绑定交换机
        channel.queueBind(normal_queue,NORMAL_EXCAHNGE,"zhangsan");
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");

        System.out.println("等待接收消息");
        DeliverCallback deliverCallback = (consumerTag, delivery) ->{
            String message = new String(delivery.getBody(), "UTF-8");
            if(message.equals("info5")){
                System.out.println("Consumer01 接收到消息" + message + "并拒绝签收该消息");
                //requeue 设置为 false 代表拒绝重新入队 该队列如果配置了死信交换机将发送到死信队列中
                channel.basicReject(delivery.getEnvelope().getDeliveryTag(), false);
            }else {
                System.out.println("Consumer01 接收到消息"+message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(normal_queue,false,deliverCallback,consumerTag -> {});
    }
}
