package com.ins.learning.springbootlearningdemorabbitmq.mqdemo;

import com.ins.learning.springbootlearningdemorabbitmq.pojo.Mail;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListeners({
        @RabbitListener(bindings = @QueueBinding(value = @Queue("myFanoutQueue-one"), exchange = @Exchange(value = "myFanoutExchange", type = ExchangeTypes.FANOUT))),
        @RabbitListener(bindings = @QueueBinding(value = @Queue("myFanoutQueue-two"), exchange = @Exchange(value = "myFanoutExchange", type = ExchangeTypes.FANOUT))),
})
public class MyFanoutListener {

    @RabbitHandler
    public void onMessage(@Payload String msg, @Headers Map<String, Object> headers, Channel channel, Message message) throws IOException {
        System.out.println("来自" + headers.get(AmqpHeaders.CONSUMER_QUEUE) + "的消息msg:" + msg);
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
       /* boolean success = true;
        if (success) {
            channel.basicAck(tag, false);
        } else {
            channel.basicNack(tag, false, true);
        }*/

    }

    @RabbitHandler
    public void onMessage(@Payload Mail mail, @Headers Map<String, Object> headers, Channel channel, Message message) throws IOException {
        System.out.println("来自" + headers.get(AmqpHeaders.CONSUMER_QUEUE) + "的消息:" + mail);
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        boolean success = true;
       /* if (success) {
            channel.basicAck(tag, false);
        } else {
            channel.basicNack(tag, false, true);
        }*/

    }
}
