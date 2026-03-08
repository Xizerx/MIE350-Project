package com.example.cms.controller;

import com.example.cms.model.entity.Order;
import com.example.cms.model.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/orders")
    List<Order> retrieveAllOrders() {
        return repository.findAll();
    }

    @PostMapping("/orders")
    Order createOrder(@RequestBody Order order) {
        return repository.save(order);
    }

    @GetMapping("/orders/{id}")
    Order retrieveOrder(@PathVariable("id") Integer orderId) {
        return repository.findById(orderId).orElse(null);
    }

    @PutMapping("/orders/{id}")
    Order updateOrder(@RequestBody Order order, @PathVariable("id") Integer orderId) {
        return repository.findById(orderId)
                .map(existingOrder -> {
                    existingOrder.setCustomer_name(order.getCustomer_name());
                    existingOrder.setCustomer_email(order.getCustomer_email());
                    existingOrder.setStatus(order.getStatus());
                    existingOrder.setCreated_at(order.getCreated_at());
                    return repository.save(existingOrder);
                })
                .orElseGet(() -> {
                    order.setOrder_id(orderId);
                    return repository.save(order);
                });
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable("id") Integer orderId) {
        repository.deleteById(orderId);
    }
}