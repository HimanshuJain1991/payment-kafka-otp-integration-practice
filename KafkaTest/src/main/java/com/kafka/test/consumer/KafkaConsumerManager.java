package com.kafka.test.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.test.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumerManager {
    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String,Object> factory;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderConsumer orderConsumer;

    // Store running consumers
    Map<String, List<ConcurrentMessageListenerContainer<String, Object>>> containers = new HashMap<>();
   // private Map<String, ConcurrentMessageListenerContainer<String,Object>> containers= new HashMap<>();
    //private Map<String, ConcurrentMessageListenerContainer<String, Object>> containers = new HashMap<>();
//    public void startConsumer(String groupId) {
//
//        // prevent duplicate consumer
//        if (containers.containsKey(groupId)) {
//            System.out.println("Consumer already running for group: " + groupId);
//            return;
//        }
//
//        // create container
//        ConcurrentMessageListenerContainer<String, Object> container =
//                factory.createContainer("order-topic");
//
//        // set groupId dynamically
//        container.getContainerProperties().setGroupId(groupId);
//
//        // set message listener (your logic)
////        container.setupMessageListener((MessageListener<String, Object>) record -> {
////            System.out.println("Received message: " + record.value());
////        });
//        container.setupMessageListener((MessageListener<String, Object>) record -> {
//            try {
//                String message = (String) record.value();
//
//                // convert JSON → OrderEvent
//                OrderEvent event = objectMapper.readValue(message, OrderEvent.class);
//
//                // call your old logic
//                orderConsumer.consume(event);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        // start consumer
//        container.start();
//
//        // store container
//        containers.put(groupId, container);
//
//        System.out.println("Consumer started for group: " + groupId);
//    }
public void startConsumer(String groupId) {

    ConcurrentMessageListenerContainer<String, Object> container =
            factory.createContainer("order-topic");

    container.getContainerProperties().setGroupId(groupId);

    container.setupMessageListener((MessageListener<String, Object>) record -> {
        System.out.println(
                "Thread: " + Thread.currentThread().getName() +
                        " | Group: " + groupId +
                        " | Message: " + record.value()
        );
    });

    container.start();

    // store multiple consumers
    containers.computeIfAbsent(groupId, k -> new ArrayList<>()).add(container);

    System.out.println("New consumer started for group: " + groupId);
}
//    public void stopConsumer(String groupId) {
//
//        // check if consumer exists
//        if (!containers.containsKey(groupId)) {
//            System.out.println("No consumer found for group: " + groupId);
//            return;
//        }
//
//        // get container
//        ConcurrentMessageListenerContainer<String, Object> container = containers.get(groupId);
//
//        // stop consumer
//        container.stop();
//
//        // remove from map
//        containers.remove(groupId);
//
//        System.out.println("Consumer stopped for group: " + groupId);
//    }


    public void stopConsumer(String groupId) {

        if (!containers.containsKey(groupId)) {
            System.out.println("No consumers found for group: " + groupId);
            return;
        }

        List<ConcurrentMessageListenerContainer<String, Object>> groupContainers = containers.get(groupId);

        // stop one consumer (not all)
        ConcurrentMessageListenerContainer<String, Object> container = groupContainers.remove(0);
        container.stop();

        System.out.println("One consumer stopped for group: " + groupId);

        // if no more consumers, remove group
        if (groupContainers.isEmpty()) {
            containers.remove(groupId);
        }
    }

}
