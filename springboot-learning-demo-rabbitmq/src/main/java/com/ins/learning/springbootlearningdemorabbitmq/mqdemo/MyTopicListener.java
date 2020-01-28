package com.ins.learning.springbootlearningdemorabbitmq.mqdemo;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyTopicListener {


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "spring.test.queue.2"),
            exchange = @Exchange(value = "spring.test.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = {"x.#"}))
    @RabbitHandler
    public void listen2(String msg,Channel channel, Message message) throws IOException {
        System.out.println("listen2 接收到消息：" + msg);
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        //channel.basicAck(tag,false);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "spring.test.queue"),
            exchange = @Exchange(value = "spring.test.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = {"x.#"}))
    @RabbitHandler
    public void listen(String msg) {
        System.out.println("listen 接收到消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ins.demo.queue.3"),
            exchange = @Exchange(value = "spring.test.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = {"x.#"}))
    @RabbitHandler
    public void listen3(String msg) {
        System.out.println("listen3 接收到消息：" + msg);
    }
}
