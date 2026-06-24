package com.cscorner.helloapp.controller;

import com.cscorner.helloapp.dto.OrderDTO;
import com.cscorner.helloapp.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    // Constructor Injection
    public OrderController(OrderService service) {
        this.service = service;
    }

    // @PostMapping
    // public String createOrder(@RequestBody OrderDTO orderDTO) {
    // return service.createOrder(orderDTO);
    // }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO response = service.createOrder(orderDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("all")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable int id) {
        return service.getOrderById(id);

    }
}
