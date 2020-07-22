package honger1234.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageLisenerController {
    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> mess){
        System.out.println("消费者一号，接收到的消息------->"+mess.getPayload()+"\t port:"+port);
    }
}
