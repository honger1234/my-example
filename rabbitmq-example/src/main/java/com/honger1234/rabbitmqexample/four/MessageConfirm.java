package com.honger1234.rabbitmqexample.four;

import com.honger1234.rabbitmqexample.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 发布确认
 * 1.单个确认
 * 2.批量确认
 * 3.异步确认
 */
public class MessageConfirm {

    public static final int MESSAGE_COUNT = 1000;

    /**
     * 单个发布确认
     *
     * @throws Exception
     */
    public static void publishMessageIndividually() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            boolean b = channel.waitForConfirms();
            if (b) {
                System.out.println("消息发送成功");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "消息时," + "单个确认耗时：" + (end - begin) + "ms");
    }

    /**
     * 批量确认
     *
     * @throws Exception
     */
    public static void publishMessageBatch() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        //批量确认消息大小
        int batchSize = 100;
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        for (int i = 1; i <= MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            //每100条消息确认一次
            if (i % batchSize == 0) {
                channel.waitForConfirms();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "消息时," + "批量确认耗时：" + (end - begin) + "ms");
    }


    /**
     * 异步确认
     *
     * @throws Exception
     */
    public static void publishMessageAcync() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        /**
         * 线程安全有序的一个哈希表，适用于高并发的情况
         * 1.轻松的将序号与消息进行关联
         * 2.轻松批量删除条目 只要给到序列号
         * 3.支持并发访问
         */
        ConcurrentSkipListMap<Long, String> outstandingConfirm = new ConcurrentSkipListMap<Long, String>();

        //确认收到消息的回调
        ConfirmCallback confirmCallback = (sequenceNumber, multiple) -> {
            if (multiple) {////清除该部分未确认消息
                ConcurrentNavigableMap<Long, String> longStringConcurrentNavigableMap = outstandingConfirm.headMap(sequenceNumber);
                longStringConcurrentNavigableMap.clear();
            }else {
                outstandingConfirm.remove(sequenceNumber);
            }
            System.out.println("发布消息确认" + sequenceNumber);
        };
        //未收到消息的回调
        ConfirmCallback confirmCallback1 = (sequenceNumber, multiple) -> {
            String message = outstandingConfirm.get(sequenceNumber);
            System.out.println("发布的消息"+message+"未被确认，序列号"+sequenceNumber);
        };
        /**
         * 添加一个异步确认的监听器
         * 1.确认收到消息的回调
         * 2.未收到消息的回调
         */
        channel.addConfirmListener(confirmCallback, confirmCallback1);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            /**
             * channel.getNextPublishSeqNo()获取下一个消息的序列号
             * 通过序列号与消息体进行一个关联
             * 全部都是未确认的消息体
             */
            outstandingConfirm.put(channel.getNextPublishSeqNo(),message);
            channel.basicPublish("", queueName, null, message.getBytes());
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "消息时," + "异步确认耗时：" + (end - begin) + "ms");
    }

    public static void main(String[] args) throws Exception {
        //1.单个确认
//        publishMessageIndividually();//发布1000消息时,单个确认耗时：775ms
        //2.批量确认
//        publishMessageBatch();//发布1000消息时,单个确认耗时：90ms
        //3.异步确认
        publishMessageAcync();
    }
}
