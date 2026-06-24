package com.cscorner.helloapp.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

    @KafkaListener(topics = "hello-topic", groupId = "hello-group")
    public void consume(String message) {
        System.out.println("✅ Kafka message received: " + message);
    }
}