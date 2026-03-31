package com.kafka.test.controller;

import com.kafka.test.consumer.KafkaConsumerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumeController {
    @Autowired
    private KafkaConsumerManager consumerManager;

    // START consumer
    @PostMapping("/start")
    public String startConsumer(@RequestParam String groupId) {
        consumerManager.startConsumer(groupId);
        return "Consumer started for group: " + groupId;
    }

    // STOP consumer
    @PostMapping("/stop")
    public String stopConsumer(@RequestParam String groupId) {
        consumerManager.stopConsumer(groupId);
        return "Consumer stopped for group: " + groupId;
    }
}
