package com.example.cms.controller;

import com.example.cms.model.entity.Products;
import com.example.cms.model.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ProductsController {
    @Autowired
    private final ProductsRepository repository;

    public ProductsController(ProductsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    List<Products> retrieveAllProducts() {
        return repository.findAll();
    }

    @PostMapping("/products")
    Products createProduct(@RequestBody Products newProduct) {
        return repository.save(newProduct);
    }

    @GetMapping("/products/{id}")
    Optional<Products> retrieveProduct(@PathVariable("id") Integer product_id) {
        return repository.findById(product_id);
                //.orElseThrow(() -> new ProductNotFoundException(product_id));
    }

    // create a put

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable("id") Integer product_id) {
        repository.deleteById(product_id);
    }

    @GetMapping("/products/search/{searchstring}")
    List<Products> searchProduct(@PathVariable("searchstring") String searchString) {
        return repository.findProductByName(searchString);
    }
}
