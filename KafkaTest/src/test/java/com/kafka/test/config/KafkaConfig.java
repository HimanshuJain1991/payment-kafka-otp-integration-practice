package com.kafka.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.test.context.EmbeddedKafka;

@Configuration
@EmbeddedKafka(partitions = 1,topics = {"order-topic"})
public class KafkaConfig {

}
