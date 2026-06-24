package com.cscorner.helloapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cscorner.helloapp.kafka.producer.KafkaProducerService;
import org.springframework.web.bind.annotation.RequestParam;

import com.cscorner.helloapp.kafka.producer.OrderProducerService;
import com.cscorner.helloapp.dto.OrderDTO;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HelloController {

    private final KafkaProducerService kafkaProducerService;

    private final OrderProducerService orderProducerService;

    public HelloController(KafkaProducerService kafkaProducerService, OrderProducerService orderProducerService) {
        this.kafkaProducerService = kafkaProducerService;
        this.orderProducerService = orderProducerService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Helloo from cs";
    }

    @GetMapping("/send-to-kafka")
    public String sendToKafka(@RequestParam String message) {
        kafkaProducerService.send(message);
        return "Message sent to Kafka";
    }

    // @GetMapping("/send-order")
    // public String sendOrder() {
    // OrderDTO order = new OrderDTO(1, "Laptop", 75000.0);
    // OrderDTO order1 = new OrderDTO(2, "Phone", 50000.0);
    // orderProducerService.sendOrder(order);
    // orderProducerService.sendOrder(order1);
    // return "Order sent to Kafka";
    // }

    @PostMapping("/send-order")
    public String sendOrder(@RequestBody OrderDTO order) {
        orderProducerService.sendOrder(order);
        return "Order sent to Kafka";
    }
}
