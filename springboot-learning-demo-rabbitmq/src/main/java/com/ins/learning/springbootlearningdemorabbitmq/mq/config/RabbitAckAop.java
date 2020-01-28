package com.ins.learning.springbootlearningdemorabbitmq.mq.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class RabbitAckAop {
    @Pointcut(value = "@annotation(com.ins.learning.springbootlearningdemorabbitmq.mq.config.RabbitAck)")
    public void cutService() {

    }

    @Around("cutService()")
    public Object manualAck(ProceedingJoinPoint point) throws Throwable {
        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();
        Object[] args = point.getArgs();
        Channel channel = null;
        Message message = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Channel) {
                channel = (Channel) args[i];
            } else if (args[i] instanceof Message) {
                message = (Message) args[i];
            }
        }
        long tag = message.getMessageProperties().getDeliveryTag();

        Object result ;
        try {
            result = point.proceed();
            channel.basicAck(tag, false);
            log.info("sssss");
        } catch (Exception e) {
            log.error("xxxx", e);
            channel.basicNack(tag, false, false);
            throw e;
        }
        return result;
    }


}
