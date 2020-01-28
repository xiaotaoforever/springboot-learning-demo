package com.ins.learning.springbootlearningdemorabbitmq.mq.config;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RabbitAck {

}
