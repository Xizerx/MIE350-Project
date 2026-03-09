package com.example.cms.controller.exceptions;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(Integer orderItemId) {
        super("Could not find order item " + orderItemId);
    }
}
