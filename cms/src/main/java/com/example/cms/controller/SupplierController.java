package com.example.cms.controller;

import com.example.cms.model.entity.Supplier;
import com.example.cms.controller.exceptions.SupplierNotFoundException;
import com.example.cms.model.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SupplierController {
    @Autowired
    private final SupplierRepository repository;

    public SupplierController(SupplierRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/suppliers")
    List<Supplier> retrieveAllSuppliers() {
        return repository.findAll();
    }

    @GetMapping("/suppliers/{supplierId}")
    Supplier retrieveSupplier(@PathVariable("supplierId") Integer supplierId) {
        return repository.findById(supplierId)
                .orElseThrow(() -> new SupplierNotFoundException(supplierId));
    }

}