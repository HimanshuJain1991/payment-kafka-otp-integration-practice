//package com.kafka.test;
//
//import com.kafka.test.producer.OrderProducer;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//
//@SpringBootTest
//@EmbeddedKafka(partitions = 1, topics = {"order-topic"})
//public class KafkaFlowTest {
//
//    @Autowired
//    private OrderProducer producer;
//
//    @Test
//    void testKafkaFlow() throws Exception {
//        producer.sendMessage();
//
//        Thread.sleep(2000); // wait for consumer
//    }
//}