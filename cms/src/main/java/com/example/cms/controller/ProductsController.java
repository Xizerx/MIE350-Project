package com.example.cms.controller;

import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.Products;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ProductsController {

    private final ProductsRepository repository;
    private final CustomerRepository customerRepository; // NEW

    @Autowired
    public ProductsController(ProductsRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository; // NEW
    }

    // ... (Keep your existing GET, POST, DELETE methods here) ...

    // NEW: Specialized Feature - Size Profile Matching
    // NEW: Storefront endpoint for active products only
    @GetMapping("/products/active")
    List<Products> retrieveActiveProducts() {
        return repository.findByActiveTrue();
    }
    @GetMapping("/products/customer/{customerId}/matched")
    List<Products> getMatchedProductsForCustomer(@PathVariable("customerId") Long customerId) {
        // 1. Fetch the customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        List<Products> matchedProducts = new ArrayList<>();

        // 2. Match Nails
        if (customer.getPreferredNailSize() != null) {
            matchedProducts.addAll(repository.findByCategoryAndSizeAndActiveTrue("nails", customer.getPreferredNailSize()));
        }

        // 3. Match Necklaces
        if (customer.getPreferredNecklaceLength() != null) {
            matchedProducts.addAll(repository.findByCategoryAndSizeAndActiveTrue("necklaces", customer.getPreferredNecklaceLength()));
        }

        // 4. Match Sunglasses
        if (customer.getPreferredSunglassesSize() != null) {
            matchedProducts.addAll(repository.findByCategoryAndSizeAndActiveTrue("sunglasses", customer.getPreferredSunglassesSize()));
        }

        return matchedProducts;
    }
}