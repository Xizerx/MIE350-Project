package com.example.cms.controller;

import com.example.cms.controller.exceptions.OrderItemNotFoundException;
import com.example.cms.model.entity.OrderItem;
import com.example.cms.model.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class OrderItemController {
    @Autowired
    private final OrderItemRepository repository;

    public OrderItemController(OrderItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/orderItems")
    List<OrderItem> retrieveAllOrderItem() {
        return repository.findAll();
    }

    @GetMapping("/orderItems/{orderItemId}")
    OrderItem retrieveOrderItems(@PathVariable("orderItemId") Integer orderItemId) {
        return repository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException(orderItemId));
    }
    @GetMapping("/orderItems/by-order/{orderId}")
    List<OrderItem> retrieveItemsByOrderId(@PathVariable("orderId") Integer orderId) {
        return repository.findItemsByOrderId(orderId);
    }

}