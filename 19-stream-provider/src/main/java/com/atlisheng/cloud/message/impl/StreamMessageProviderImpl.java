package com.atlisheng.cloud.message.impl;

import com.atlisheng.cloud.message.StreamMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 注入了消息输出对象output,MessageChannel对象，在send方法中使用output发送消息，使用@EnableBinding(Source.class)注解
 * 【元注解有@Configuration】引入该消息发送对象，并在控制器方法进行调用
 * @创建日期 2023/11/13
 * @since 1.0.0
 */
@Slf4j
@EnableBinding(Source.class) // 将这个注解理解为是一个消息的发送管道的定义，实现的接口不用加任何注解，对应的方法必须是send
public class StreamMessageProviderImpl implements StreamMessageProvider {
    @Resource
    private MessageChannel output; // 消息的发送管道，显然这是项目启动自动配置的

    /**
     * @return {@link String }
     * @描述 send方法中封装了对output的调用来发送消息，这个方法的名字应该可以不固定为send
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/13
     * @since 1.0.0
     */
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        //这个MessageBuilder是org.springframework.integration.support包下的
        //收消息用message.getPayload()方法，发消息用MessageBuilder.withPayload(serial).build()方法
        this.output.send(MessageBuilder.withPayload(serial).build()); // 创建并发送消息
        log.info("serial: "+serial);
        return serial;
    }
}
