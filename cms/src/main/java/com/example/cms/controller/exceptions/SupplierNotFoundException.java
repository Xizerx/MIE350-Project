package com.example.cms.controller.exceptions;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(Integer supplierId) {
        super("Could not find supplier " + supplierId);
    }
}
