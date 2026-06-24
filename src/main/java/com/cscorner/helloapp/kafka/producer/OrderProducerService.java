package com.cscorner.helloapp.kafka.producer;

import com.cscorner.helloapp.dto.OrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public OrderProducerService(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderDTO order) {
        kafkaTemplate.send("multi-order-topic", order);
    }
}
