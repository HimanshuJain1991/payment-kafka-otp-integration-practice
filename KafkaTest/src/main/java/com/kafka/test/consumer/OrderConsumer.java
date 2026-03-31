package com.kafka.test.consumer;

import com.kafka.test.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    //@KafkaListener(topics = "order-topic", groupId = "group1")
    public void consume(OrderEvent event) {
        System.out.println("Payment Processing: " + event.getOrderId());
    }
}