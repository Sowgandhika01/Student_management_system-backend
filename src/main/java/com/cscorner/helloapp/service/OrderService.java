package com.cscorner.helloapp.service;

import com.cscorner.helloapp.dto.OrderDTO;
import com.cscorner.helloapp.kafka.producer.OrderProducerService;
import com.cscorner.helloapp.model.Order;
import com.cscorner.helloapp.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderProducerService producerService;
    private final OrderRepository repository;

    public OrderService(OrderProducerService producerService, OrderRepository repository) {
        this.producerService = producerService;
        this.repository = repository;
    }

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = new Order();
        order.setProduct(dto.getProduct());
        order.setPrice(dto.getPrice());
        order.setStatus("CREATED");

        Order saved = repository.save(order);
        dto.setId(saved.getId());

        try {
            producerService.sendOrder(dto);
        } catch (Exception ex) {
            // Order is already persisted; keep API successful even if broker is down.
            log.error("Order saved but Kafka publish failed for id {}", saved.getId(), ex);
        }

        return dto;
    }

    public OrderDTO getOrderById(int id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        return new OrderDTO(order.getId(), order.getProduct(), order.getPrice());
    }

    public List<OrderDTO> getAllOrders() {
        return repository.findAll().stream()
                .map(order -> new OrderDTO(order.getId(), order.getProduct(), order.getPrice()))
                .collect(Collectors.toList());
    }
}
