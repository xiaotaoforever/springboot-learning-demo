package com.ins.learning.springbootlearningdemorabbitmq.mqdemo;

import com.ins.learning.springbootlearningdemorabbitmq.pojo.Mail;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void testSend4Topic() throws InterruptedException {
        String msg = "hello, Spring boot amqp testSend4Topic";
        this.amqpTemplate.convertAndSend("spring.test.exchange", "x.b.c", msg);
        //使用错误的exchange，会执行rabbitTemplate.setConfirmCallback中设定的回调方法
        //this.amqpTemplate.convertAndSend("spring.test.exchange"+111, "x.b", msg);
    }

    public void testSend4Topic2() throws InterruptedException {
        String msg = "hello, Spring boot amqp testSend4Topic2";
        this.amqpTemplate.convertAndSend("x.cc", msg);
        // 等待10秒后再结束
        Thread.sleep(10000);
    }

    public void testSend4Fanout() throws InterruptedException {
        //(交换机,routingKey,消息内容),routingKey随意
        amqpTemplate.convertAndSend("myFanoutExchange", "", "this is a message: testSend4Fanout");
    }

    public void testSend4Pojo() throws InterruptedException {
        Mail mail = new Mail();
        mail.setTitle("title xxx");
        mail.setContent("content xx");
        mail.setTo("aaa@mail.com");
        mail.setMsgId(LocalDateTime.now().toString());
        //(交换机,routingKey,消息内容),routingKey随意
        amqpTemplate.convertAndSend("myDirectExchange", "mine.direct", mail);

    }

    public void testSend4Direct() throws InterruptedException {
        //(交换机,routingKey,消息内容),routingKey随意
        amqpTemplate.convertAndSend("myDirectExchange", "mine.direct", "this is a message: testSend4Direct");

    }

    public void testSend4Header() {
        amqpTemplate.convertAndSend("myHeadExchange", "", "this is a message", message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setHeader("key-one", "1");
            return message;
        });
    }

}
