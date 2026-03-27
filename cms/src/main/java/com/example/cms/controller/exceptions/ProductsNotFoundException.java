package com.example.cms.controller.exceptions;

public class ProductsNotFoundException extends RuntimeException{
    public ProductsNotFoundException(Integer id) {
        super("Could not find product " + id);
    }
}
