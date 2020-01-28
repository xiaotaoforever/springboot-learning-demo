package com.ins.learning.springbootlearningdemorabbitmq.mqdemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerTest {

    @Autowired
    private Producer producer;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSend4Topic() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            producer.testSend4Topic();
        }
        Thread.sleep(5000);
    }

    @Test
    void testSend4Fanout() throws InterruptedException {
        producer.testSend4Fanout();
        Thread.sleep(10000);
    }

    @Test
    void testSend4Direct() throws InterruptedException {
        producer.testSend4Direct();
        Thread.sleep(5000);
    }

    @Test
    void testSend4Pojo() throws InterruptedException {
        producer.testSend4Pojo();
        Thread.sleep(10000);
    }

    @Test
    void testSend4Header() throws InterruptedException {
        producer.testSend4Header();
        Thread.sleep(10000);
    }


}