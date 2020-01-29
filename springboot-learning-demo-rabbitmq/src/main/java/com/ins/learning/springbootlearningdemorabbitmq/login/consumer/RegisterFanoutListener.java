package com.ins.learning.springbootlearningdemorabbitmq.login.consumer;

import com.ins.learning.springbootlearningdemorabbitmq.pojo.Mail;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class RegisterFanoutListener {

    @RabbitListener(bindings = @QueueBinding(value = @Queue("register.mail"), exchange = @Exchange(value = "register.fanout.exchange", type = ExchangeTypes.FANOUT)))
    @RabbitHandler
    public void onMessage(@Payload Mail mail, @Headers Map<String, Object> headers, Channel channel, Message message) {
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        /*boolean success = true;
        if (success) {
            channel.basicAck(tag, false);
        } else {
            channel.basicNack(tag, false, true);
        }*/
        log.info("==========来自{}的消息: {}", headers.get(AmqpHeaders.CONSUMER_QUEUE), mail.toString());

    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue("register.push"), exchange = @Exchange(value = "register.fanout.exchange", type = ExchangeTypes.FANOUT)))
    @RabbitHandler
    //@RabbitAck /*消费者自动提交时，可通过aop形式确认，减少重复代码*/
    public void onMessage2(@Payload Mail mail, @Headers Map<String, Object> headers, Channel channel, Message message) {
        try {
            log.info("==========来自{}的消息: {}", headers.get(AmqpHeaders.CONSUMER_QUEUE), mail.toString());
            int i = 1 / 0;//throw exception
        } catch (Exception e) {
            log.error("eeee", e);
            throw e;
        }

    }
}
