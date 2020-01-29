package com.ins.learning.springbootlearningdemorabbitmq.login.controller;

import com.ins.learning.springbootlearningdemorabbitmq.pojo.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    Jackson2JsonMessageConverter jsonMessageConverter;
    @RequestMapping("/")
    public String register(){
        Random r1 = new Random();
        log.info("=========== register : success!");
        String msgId = String.valueOf(r1.nextInt());
        Mail mail = new Mail();
        mail.setMsgId(msgId);
        mail.setContent("content : " + msgId);
        mail.setTitle("title : register mail!");
        mail.setTo("mail to : " + msgId);
        registerNotice(msgId, mail);
        registerIntegral(msgId);
        return "register: success!";
    }

    private void registerIntegral(String msgId) {
        //2. 注册成功后通知积分服务：赠送积分
        String s = "register success , send you 100$!!!!";
        CorrelationData correlationData2 = new CorrelationData(msgId);
        correlationData2.setReturnedMessage(jsonMessageConverter.toMessage(s,new MessageProperties()));
        rabbitTemplate.convertAndSend("myDirectExchange", "mine.direct", s,correlationData2);
    }

    private void registerNotice(String msgId, Mail mail) {
        //注册成功后通知邮件服务：通过订阅形式，发送邮件和推送推送
        CorrelationData correlationData1 = new CorrelationData(msgId);
        correlationData1.setReturnedMessage(jsonMessageConverter.toMessage(mail,null));
        rabbitTemplate.convertAndSend("register.fanout.exchange", "", mail,correlationData1);
    }
}
