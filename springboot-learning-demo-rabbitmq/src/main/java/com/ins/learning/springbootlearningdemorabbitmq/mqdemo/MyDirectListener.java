package com.ins.learning.springbootlearningdemorabbitmq.mqdemo;

import com.ins.learning.springbootlearningdemorabbitmq.pojo.Mail;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue("myDirectQueue"), key = "mine.direct", exchange = @Exchange(value = "myDirectExchange")
))
@Slf4j
public class MyDirectListener {
    @Autowired
    Jackson2JsonMessageConverter jsonMessageConverter;
    /**
     * listenerAdapter
     *
     * @param msg 消息内容,当只有一个参数的时候可以不加@Payload注解
     */
    @RabbitHandler
    public void onMessage(@Payload String msg, Channel channel, Message message) throws IOException {
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        //channel.basicAck(tag,false);
        log.info("==========来自{}的消息，{}","myDirectQueue",msg);
    }


    @RabbitHandler
    public void onMessage(@Payload Mail mail, Channel channel, Message message) throws IOException {
        System.out.println("direct exchange mail: " + mail.toString());
        Mail mail1 = (Mail) jsonMessageConverter.fromMessage(message, Mail.class);
        System.out.println("direct exchange mail222: " + mail1.toString());

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        //channel.basicAck(tag,false);
    }

}
