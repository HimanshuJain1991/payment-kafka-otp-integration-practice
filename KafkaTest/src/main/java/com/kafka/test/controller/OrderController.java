package com.kafka.test.controller;

import com.kafka.test.dto.OrderEvent;
import com.kafka.test.producer.OrderProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String send(@RequestBody OrderEvent event) {
        producer.sendMessage(event);
        return "Order sent to Kafka";
    }
}