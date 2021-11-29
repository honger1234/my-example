package com.honger1234.rabbitmqexample.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class TtlQueueConfig {
    public static final String X_EXCHANGE = "X";
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String DEAD_LETTER_QUEUE = "QD";

    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }

    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    @Bean("queueA")
    public Queue queueA(){
        Map<String, Object> map = new HashMap<>();
        //死信交换机
        map.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
        //死信routkey
        map.put("x-dead-letter-routing-key","YD");
        //声明队列ttl
        map.put("x-message-ttl",10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(map).build();
    }

    @Bean("queueB")
    public Queue queueB(){
        Map<String, Object> map = new HashMap<>();
        //死信交换机
        map.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);
        //死信routkey
        map.put("x-dead-letter-routing-key","YD");
        //声明队列ttl
        map.put("x-message-ttl",20000);
        return QueueBuilder.durable(QUEUE_B).withArguments(map).build();
    }

    @Bean("queueD")
    public Queue queueD(){
        return new Queue(DEAD_LETTER_QUEUE);
    }

    //将队列A和X交换机绑定
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange exchangeX){
        return BindingBuilder.bind(queueA).to(exchangeX).with("XA");
    }

    //将队列B和X交换机绑定
    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange exchangeX){
        return BindingBuilder.bind(queueB).to(exchangeX).with("XB");
    }

    //将队列D和Y交换机绑定
    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange exchangeY){
        return BindingBuilder.bind(queueD).to(exchangeY).with("YD");
    }


}
