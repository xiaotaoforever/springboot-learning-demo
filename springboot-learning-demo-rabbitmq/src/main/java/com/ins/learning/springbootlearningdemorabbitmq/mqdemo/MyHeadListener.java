package com.ins.learning.springbootlearningdemorabbitmq.mqdemo;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class MyHeadListener {

    /**
     * 任意匹配
     *
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("headQueue-one"),
            exchange = @Exchange(value = "myHeadExchange", type = ExchangeTypes.HEADERS),
            arguments = {
                    @Argument(name = "key-one", value = "1"),
                    @Argument(name = "key-two", value = "2"),
                    @Argument(name = "x-match", value = "any")
            }))
    public void anyMatchOnMessage(String msg) {
        System.out.println("来自 headQueue-one " + msg);
    }


    /**
     * 全匹配
     *
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("headQueue-two"),
            exchange = @Exchange(value = "myHeadExchange", type = ExchangeTypes.HEADERS),
            arguments = {
                    @Argument(name = "key-one", value = "1"),
                    @Argument(name = "x-match", value = "all")
            }))
    public void allMatchOnMessage(String msg) {
        System.out.println("来自 headQueue-two " + msg);
    }
}