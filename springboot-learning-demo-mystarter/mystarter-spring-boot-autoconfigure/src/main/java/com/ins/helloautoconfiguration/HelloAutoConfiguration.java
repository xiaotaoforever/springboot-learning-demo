package com.ins.helloautoconfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DemoCondition.class)
@ConditionalOnProperty(value = "ins.msg.enable", havingValue = "true")
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Bean
    public HelloService getHelloService(HelloProperties helloProperties){
        return new HelloService(helloProperties.getMembers());
    }

}
