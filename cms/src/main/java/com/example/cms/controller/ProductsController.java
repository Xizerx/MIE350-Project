package com.example.cms.controller;

import com.example.cms.controller.dto.ProductsDto;
import com.example.cms.controller.exceptions.ProductsNotFoundException;
import com.example.cms.controller.exceptions.SupplierNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.Products;
import com.example.cms.model.entity.Supplier;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.ProductsRepository;
import com.example.cms.model.repository.SupplierRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

    @Autowired
    private SupplierRepository supplierRepository;

    // ... (Keep your existing GET, POST, DELETE methods here) ...

    @GetMapping("/products")
    List<Products> retrieveAllProducts() {
        return repository.findAll();
    }

    @PostMapping("/products")
    Products createProduct(@RequestBody ProductsDto productsDto) {
        Products newProduct = new Products();
        newProduct.setProduct_id(productsDto.getProduct_id());
        newProduct.setName(productsDto.getName());
        newProduct.setDescription(productsDto.getDescription());
        newProduct.setCategory(productsDto.getCategory());
        newProduct.setPrice(productsDto.getPrice());
        newProduct.setSize(productsDto.getSize());
        newProduct.setColor(productsDto.getColor());
        newProduct.setVariant(productsDto.getVariant());
        newProduct.setIs_bundle(productsDto.getIs_bundle());
        newProduct.setImageUrl(productsDto.getImageUrl());
        newProduct.setActive(productsDto.getActive());

        Supplier supplier = supplierRepository.findById(productsDto.getSupplier_id()).orElseThrow(
                () -> new SupplierNotFoundException(productsDto.getSupplier_id()));
        newProduct.setSupplier(supplier);
        return repository.save(newProduct);
    }

    @GetMapping("/products/{id}")
    Products retrieveProduct(@PathVariable("id") Integer productCode) {
        return repository.findById(productCode)
                .orElseThrow(() -> new ProductsNotFoundException(productCode));
    }

    @PutMapping("/products/{id}")
    Products updateProduct(@RequestBody ProductsDto productsDto, @PathVariable("id") Integer productCode) {
        return repository.findById(productCode)
                .map(product -> {
                    product.setProduct_id(productsDto.getProduct_id());
                    product.setName(productsDto.getName());
                    product.setDescription(productsDto.getDescription());
                    product.setCategory(productsDto.getCategory());
                    product.setPrice(productsDto.getPrice());
                    product.setSize(productsDto.getSize());
                    product.setColor(productsDto.getColor());
                    product.setVariant(productsDto.getVariant());
                    product.setIs_bundle(productsDto.getIs_bundle());
                    product.setImageUrl(productsDto.getImageUrl());
                    product.setActive(productsDto.getActive());

                    Supplier supplier = supplierRepository.findById(productsDto.getSupplier_id()).orElseThrow(
                            () -> new SupplierNotFoundException(productsDto.getSupplier_id()));
                    product.setSupplier(supplier);
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    Products newProduct = new Products();
                    newProduct.setProduct_id(productsDto.getProduct_id());
                    newProduct.setName(productsDto.getName());
                    newProduct.setDescription(productsDto.getDescription());
                    newProduct.setCategory(productsDto.getCategory());
                    newProduct.setPrice(productsDto.getPrice());
                    newProduct.setSize(productsDto.getSize());
                    newProduct.setColor(productsDto.getColor());
                    newProduct.setVariant(productsDto.getVariant());
                    newProduct.setIs_bundle(productsDto.getIs_bundle());
                    newProduct.setImageUrl(productsDto.getImageUrl());
                    newProduct.setActive(productsDto.getActive());

                    Supplier supplier = supplierRepository.findById(productsDto.getSupplier_id()).orElseThrow(
                            () -> new SupplierNotFoundException(productsDto.getSupplier_id()));
                    newProduct.setSupplier(supplier);
                    return repository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable("id") Integer productCode) {
        repository.deleteById(productCode);
    }


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