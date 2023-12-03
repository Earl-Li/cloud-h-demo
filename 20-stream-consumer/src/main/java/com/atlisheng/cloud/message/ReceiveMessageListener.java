package com.atlisheng.cloud.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)//标明这是一个消息消费者监听器
@Slf4j
public class ReceiveMessageListener {
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)//监听到消息执行的方法，会自动传参Message类型的消息到该方法中
    public void input(Message<String> message) {
        //收消息用message.getPayload()方法，发消息用MessageBuilder.withPayload(serial).build()方法
        log.info("1号消费者接收到的消息:" + message.getPayload()+"\t port: "+serverPort);
    }
}